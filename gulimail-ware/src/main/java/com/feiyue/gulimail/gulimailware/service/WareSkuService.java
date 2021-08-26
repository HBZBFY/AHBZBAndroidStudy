package com.feiyue.gulimail.gulimailware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.vo.SkuHasStockVo;
import com.feiyue.gulimail.gulimailware.entity.WareSkuEntity;

import java.util.List;
import java.util.Map;


public interface WareSkuService extends IService<WareSkuEntity> {


    PageUtils queryPage(Map<String, Object> parm);

    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);
}

