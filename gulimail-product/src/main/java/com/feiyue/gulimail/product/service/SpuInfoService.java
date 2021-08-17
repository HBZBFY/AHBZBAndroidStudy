package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.SpuInfoEntity;
import com.feiyue.gulimail.product.vo.SpuSaveVo;

import java.util.Map;

public interface SpuInfoService extends IService<SpuInfoEntity> {

    void saveSpuInfo(SpuSaveVo vo);

    PageUtils queryPage(Map<String, Object> param);
}

