package com.feiyue.gulimail.product.service.impl;


import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.product.dao.BrandDao;
import com.feiyue.gulimail.product.entity.BrandEntity;
import com.feiyue.gulimail.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;



import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> parm) {
        String key = (String) parm.get("key");
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        IPage<BrandEntity> page = this.page(new Query<BrandEntity>().getPage(parm), queryWrapper);
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.eq("brand_id", key).or().like("name", key);
        }
        return  new PageUtils(page);
    }
}