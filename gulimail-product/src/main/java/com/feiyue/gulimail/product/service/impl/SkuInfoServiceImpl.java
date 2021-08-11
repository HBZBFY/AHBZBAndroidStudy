package com.feiyue.gulimail.product.service.impl;

import com.feiyue.gulimail.product.dao.SkuInfoDao;
import com.feiyue.gulimail.product.entity.SkuInfoEntity;
import com.feiyue.gulimail.product.service.SkuInfoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {


}