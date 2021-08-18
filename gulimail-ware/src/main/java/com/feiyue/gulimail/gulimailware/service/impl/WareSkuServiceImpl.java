package com.feiyue.gulimail.gulimailware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.gulimailware.dao.WareSkuDao;
import com.feiyue.gulimail.gulimailware.entity.WareSkuEntity;
import com.feiyue.gulimail.gulimailware.service.WareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> parm) {
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) parm.get("key");
        queryWrapper.eq("sku_d", key).or().like("sku_name", key);
        IPage<WareSkuEntity> page = this.page(new Query<WareSkuEntity>().getPage(parm), queryWrapper);
        return new PageUtils(page);
    }
}