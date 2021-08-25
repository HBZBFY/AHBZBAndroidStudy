package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.gulimail.product.entity.SkuInfoEntity;

import java.util.List;
import java.util.Map;

public interface SkuInfoService extends IService<SkuInfoEntity> {

    List<SkuInfoEntity> getSkusBySpuId(Long spuId);
}

