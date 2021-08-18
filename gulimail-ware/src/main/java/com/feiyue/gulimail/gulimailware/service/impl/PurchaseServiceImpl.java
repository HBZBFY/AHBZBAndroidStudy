package com.feiyue.gulimail.gulimailware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.gulimailware.dao.PurchaseDao;
import com.feiyue.gulimail.gulimailware.entity.PurchaseDetailEntity;
import com.feiyue.gulimail.gulimailware.entity.PurchaseEntity;
import com.feiyue.gulimail.gulimailware.service.PurchaseDetailService;
import com.feiyue.gulimail.gulimailware.service.PurchaseService;
import com.feiyue.gulimail.gulimailware.service.WareInfoService;
import com.feiyue.gulimail.gulimailware.vo.MergeVo;
import com.feiyue.gulimail.gulimailware.vo.PurchaseDoneVo;
import com.feiyue.gulimail.gulimailware.vo.PurchaseItemDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {


    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Autowired
    private WareInfoService wareInfoService;

    @Override
    public void mergePurchase(MergeVo mergeVo) {
        Long purchaseId = mergeVo.getPurchaseId();
        if(purchaseId == null){
            //1、新建一个
            PurchaseEntity purchaseEntity = new PurchaseEntity();

            purchaseEntity.setStatus(1);
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            this.save(purchaseEntity);
            purchaseId = purchaseEntity.getId();
        }

        List<Long> items = mergeVo.getItems();
        Long finalPurchaseId = purchaseId;
        List<PurchaseDetailEntity> collect = items.stream().map(i -> {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();

            detailEntity.setId(i);
            detailEntity.setPurchaseId(finalPurchaseId);
            detailEntity.setStatus(1);
            return detailEntity;
        }).collect(Collectors.toList());


        purchaseDetailService.updateBatchById(collect);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseId);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }

    @Override
    public PageUtils getUnreceiveList() {
        QueryWrapper<PurchaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        IPage<PurchaseEntity> page = this.page(new Query<PurchaseEntity>().getPage(null), queryWrapper);
        return new PageUtils(page);
    }

    @Override
    public void received(Long[] ids) {
        List<Long> longs = Arrays.asList(ids);
        List<PurchaseEntity> collect = longs.stream().map(item -> {
            PurchaseEntity purchaseEntity = this.getById(item);
            purchaseEntity.setStatus(1);
            return purchaseEntity;
        }).collect(Collectors.toList());

        this.saveBatch(collect);
    }

    @Override
    public void done(PurchaseDoneVo purchaseDoneVo) {

        Long id = purchaseDoneVo.getId();


        //2、改变采购项的状态
        Boolean flag = true;
        List<PurchaseItemDoneVo> items = purchaseDoneVo.getItems();

        List<PurchaseDetailEntity> updates = new ArrayList<>();
        for (PurchaseItemDoneVo item : items) {
            PurchaseDetailEntity detailEntity = new PurchaseDetailEntity();
            if(item.getStatus() == 1){
                flag = false;
                detailEntity.setStatus(item.getStatus());
            }else{
                detailEntity.setStatus(1);
                ////3、将成功采购的进行入库
                PurchaseDetailEntity entity = purchaseDetailService.getById(item.getItemId());
              //  wareSkuService.addStock(entity.getSkuId(),entity.getWareId(),entity.getSkuNum());

            }
            detailEntity.setId(item.getItemId());
            updates.add(detailEntity);
        }

        purchaseDetailService.updateBatchById(updates);

        //1、改变采购单状态
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(id);
        purchaseEntity.setStatus(flag?0:1);
        purchaseEntity.setUpdateTime(new Date());
        this.updateById(purchaseEntity);
    }
}