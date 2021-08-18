package com.feiyue.gulimail.gulimailware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.gulimailware.entity.PurchaseDetailEntity;

import java.util.Map;

public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> parm);
}

