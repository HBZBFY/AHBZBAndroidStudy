package com.feiyue.gulimail.gulimailware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.gulimailware.entity.WareSkuEntity;

import java.util.Map;


public interface WareSkuService extends IService<WareSkuEntity> {


    PageUtils queryPage(Map<String, Object> parm);
}

