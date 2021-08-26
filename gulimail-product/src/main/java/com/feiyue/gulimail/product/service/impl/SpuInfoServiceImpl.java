package com.feiyue.gulimail.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.to.SkuReductionTo;
import com.feiyue.common.to.SpuBoundTo;
import com.feiyue.common.to.es.SkuEsModel;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.common.utils.R;
import com.feiyue.common.vo.SkuHasStockVo;
import com.feiyue.gulimail.product.dao.AttrDao;
import com.feiyue.gulimail.product.dao.SpuInfoDao;
import com.feiyue.gulimail.product.dao.SpuInfoDescDao;
import com.feiyue.gulimail.product.entity.*;
import com.feiyue.gulimail.product.feign.CouponFeignService;
import com.feiyue.gulimail.product.feign.ElasticSearchFeignService;
import com.feiyue.gulimail.product.feign.WareFeignService;
import com.feiyue.gulimail.product.service.*;
import com.feiyue.gulimail.product.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    private SpuInfoDescDao spuInfoDescDao;

    @Autowired
    private SpuImagesService spuImagesService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private AttrDao attrDao;

    @Autowired(required = false)
    private CouponFeignService couponFeignService;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuImagesService skuImagesService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired(required = false)
    private WareFeignService wareFeignService;

    @Autowired(required = false)
    private ElasticSearchFeignService elasticSearchFeignService;

    @Override
    public void saveSpuInfo(SpuSaveVo vo) {
        // 保存自己
        Supplier<SpuInfoEntity> spuInfoEntitySupplier = SpuInfoEntity::new;
        SpuInfoEntity spuInfoEntity = spuInfoEntitySupplier.get();
        BeanUtils.copyProperties(vo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        this.baseMapper.insert(spuInfoEntity);

        // 保存描述信息
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        spuInfoDescEntity.setDecript(vo.getSpuDescription());
        spuInfoDescDao.insert(spuInfoDescEntity);

        //  保存图片
        List<String> images = vo.getImages();
        spuImagesService.saveImages(images, spuInfoEntity.getId());

        // 保存产品详细信息
        List<BaseAttrs> baseAttrs = vo.getBaseAttrs();
        List<ProductAttrValueEntity> collect = baseAttrs.stream().map(item -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            AttrEntity attrEntity = attrDao.selectById(item.getAttrId());
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());
            productAttrValueEntity.setAttrId(attrEntity.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrValue(item.getAttrValues());
            productAttrValueEntity.setQuickShow(attrEntity.getShowDesc());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        productAttrValueService.saveBatch(collect);

        // 保存积分信息
        Bounds bounds = vo.getBounds();
        SpuBoundTo spuBoundTo = new SpuBoundTo();
        BeanUtils.copyProperties(bounds,spuBoundTo);
        spuBoundTo.setSpuId(spuInfoEntity.getId());
        R r = couponFeignService.saveSpuBounds(spuBoundTo);
        if(r.getCode() != 0){
            log.error("远程保存spu积分信息失败");
        }

        // 保存spu对应的sku
        List<Skus> skus = vo.getSkus();
        if (skus != null && !skus.isEmpty()) {
            skus.forEach(item -> {
                String defaultImg = "";
                List<Images> images1 = item.getImages();
                if (images1 != null && !images1.isEmpty()) {
                    for (Images images2 : images1) {
                        if (images2.getDefaultImg() == 1) {
                            defaultImg = images2.getImgUrl();
                        }
                    }
                }
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                BeanUtils.copyProperties(item,skuInfoEntity);
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setSaleCount(0L);
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                //5.1）、sku的基本信息；pms_sku_info
                skuInfoService.save(skuInfoEntity);

                Long skuId = skuInfoEntity.getSkuId();

                List<SkuImagesEntity> imagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setSkuId(skuId);
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    return skuImagesEntity;
                }).filter(entity->{
                    //返回true就是需要，false就是剔除
                    return !StringUtils.isEmpty(entity.getImgUrl());
                }).collect(Collectors.toList());
                //5.2）、sku的图片信息；pms_sku_image
                skuImagesService.saveBatch(imagesEntities);

                List<Attr> attr = item.getAttr();
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attr.stream().map(a -> {
                    SkuSaleAttrValueEntity attrValueEntity = new SkuSaleAttrValueEntity();
                    BeanUtils.copyProperties(a, attrValueEntity);
                    attrValueEntity.setSkuId(skuId);

                    return attrValueEntity;
                }).collect(Collectors.toList());
                //5.3）、sku的销售属性信息：pms_sku_sale_attr_value
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);

                // //5.4）、sku的优惠、满减等信息；gulimall_sms->sms_sku_ladder\sms_sku_full_reduction\sms_member_price
                SkuReductionTo skuReductionTo = new SkuReductionTo();
                BeanUtils.copyProperties(item,skuReductionTo);
                skuReductionTo.setSkuId(skuId);
                if(skuReductionTo.getFullCount() >0 || skuReductionTo.getFullPrice().compareTo(new BigDecimal("0")) == 1){
                    R r1 = couponFeignService.saveSkuReduction(skuReductionTo);
                    if(r1.getCode() != 0){
                        log.error("远程保存sku优惠信息失败");
                    }
                }
            });
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> param) {
        QueryWrapper<SpuInfoEntity> queryWrapper = new QueryWrapper<>();

        String key = (String) param.get("key");

        IPage<SpuInfoEntity> page = this.page(new Query<SpuInfoEntity>().getPage(param), queryWrapper);
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.like("spuName", key);
        }
        return new PageUtils(page);
    }

    @Override
    public void up(Long spuId) {

        List<SkuInfoEntity> skuInfoEntityList = skuInfoService.getSkusBySpuId(spuId);
        List<ProductAttrValueEntity> productAttrValueEntitieList = productAttrValueService.baseAttrlistforspu(spuId);
        List<Long> collect = productAttrValueEntitieList.stream().map(item -> item.getAttrId()).collect(Collectors.toList());
        List<Long> ids = attrDao.selectSearchAttrs(collect);
        Set<Long> idsSet = new HashSet<>(ids);
        List<SkuEsModel.Attrs> attrsList = new ArrayList<>();
        List<Long> skuIds = skuInfoEntityList.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
        R r = wareFeignService.getSkuHasStock(skuIds);
        List<SkuHasStockVo> skuHasStockVoList = (List<SkuHasStockVo>) r.get("data");
        Map<Long, Boolean> collect2 = skuHasStockVoList.stream().collect(Collectors.toMap(SkuHasStockVo::getSkuId, SkuHasStockVo::getHasStock));

        List<SkuEsModel.Attrs> collect1 = productAttrValueEntitieList.stream()
                .filter(item -> idsSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuEsModel.Attrs attrs = new SkuEsModel.Attrs();
                    BeanUtils.copyProperties(item, attrs);
                    return attrs;
                }).collect(Collectors.toList());

        List<SkuEsModel> list = skuInfoEntityList.stream().map(item -> {
            SkuEsModel skuEsModel = new SkuEsModel();
            BeanUtils.copyProperties(item, skuEsModel);
            skuEsModel.setSkuPrice(item.getPrice());
            skuEsModel.setSkuImg(item.getSkuDefaultImg());
            skuEsModel.setHasStock(collect2.get(item.getSkuId()));

            skuEsModel.setHotScore(0L);
            // 设置品牌信息
            BrandEntity brandEntity = brandService.getById(item.getBrandId());
            skuEsModel.setBrandImg(brandEntity.getLogo());
            skuEsModel.setBrandName(brandEntity.getName());

            // 分类信息
            CategoryEntity categoryEntity = categoryService.getById(item.getCatalogId());
            skuEsModel.setCatalogName(categoryEntity.getName());
            skuEsModel.setAttrs(collect1);
            return skuEsModel;
        }).collect(Collectors.toList());

        // ElasticSearch 存储索引
        try{
            elasticSearchFeignService.saveProduct(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}