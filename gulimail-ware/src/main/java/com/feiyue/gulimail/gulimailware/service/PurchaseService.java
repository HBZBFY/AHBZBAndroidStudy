package com.feiyue.gulimail.gulimailware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.gulimailware.entity.PurchaseEntity;
import com.feiyue.gulimail.gulimailware.vo.MergeVo;
import com.feiyue.gulimail.gulimailware.vo.PurchaseDoneVo;

public interface PurchaseService extends IService<PurchaseEntity> {


    void mergePurchase(MergeVo mergeVo);

    PageUtils getUnreceiveList();

    void received(Long[] ids);

    void done(PurchaseDoneVo purchaseDoneVo);
}

