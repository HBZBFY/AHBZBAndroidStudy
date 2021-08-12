package com.feiyue.gulimail.product.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.product.dao.CategoryBrandRelationDao;
import com.feiyue.gulimail.product.entity.CategoryBrandRelationEntity;
import com.feiyue.gulimail.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {


    @Override
    public PageUtils querypage(Map<String, Object> parm) {
        IPage<CategoryBrandRelationEntity> page = this.page(new Query<CategoryBrandRelationEntity>().getPage(parm), new QueryWrapper<>());
        return new PageUtils(page);
    }
}