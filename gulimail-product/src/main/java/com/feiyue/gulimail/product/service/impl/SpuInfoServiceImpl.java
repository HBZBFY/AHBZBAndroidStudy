package com.feiyue.gulimail.product.service.impl;


import com.feiyue.gulimail.product.dao.AttrDao;
import com.feiyue.gulimail.product.dao.SpuImagesDao;
import com.feiyue.gulimail.product.dao.SpuInfoDao;
import com.feiyue.gulimail.product.dao.SpuInfoDescDao;
import com.feiyue.gulimail.product.entity.*;
import com.feiyue.gulimail.product.service.ProductAttrValueService;
import com.feiyue.gulimail.product.service.SpuImagesService;
import com.feiyue.gulimail.product.service.SpuInfoDescService;
import com.feiyue.gulimail.product.service.SpuInfoService;
import com.feiyue.gulimail.product.vo.BaseAttrs;
import com.feiyue.gulimail.product.vo.Bounds;
import com.feiyue.gulimail.product.vo.SpuSaveVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


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

    }
}