package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.BrandEntity;


import java.util.Map;


public interface BrandService extends IService<BrandEntity> {
    PageUtils queryPage(Map<String, Object> parm);
}

