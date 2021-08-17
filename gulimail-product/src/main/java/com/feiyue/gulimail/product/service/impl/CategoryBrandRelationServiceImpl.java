package com.feiyue.gulimail.product.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.product.dao.CategoryBrandRelationDao;
import com.feiyue.gulimail.product.entity.CategoryBrandRelationEntity;
import com.feiyue.gulimail.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils querypage(Map<String, Object> parm) {
        IPage<CategoryBrandRelationEntity> page = this.page(new Query<CategoryBrandRelationEntity>().getPage(parm), new QueryWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public void updateBrandInfo(Long brandId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(brandId);
        categoryBrandRelationEntity.setBrandName(name);
        this.update(categoryBrandRelationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));
    }

    @Override
    public void updateBrandInfoByCatelog(Long catId, String name) {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setCatelogId(catId);
        categoryBrandRelationEntity.setCatelogName(name);
        this.update(categoryBrandRelationEntity, new UpdateWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
    }

    @Override
    public List<CategoryBrandRelationEntity> getBrandList(Long catId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", catId);
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = categoryBrandRelationDao.selectList(queryWrapper);
        return categoryBrandRelationEntities;
    }

    @Override
    public List<CategoryBrandRelationEntity> getCatelogList(Long brandId) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        List<CategoryBrandRelationEntity> categoryBrandRelationEntities = categoryBrandRelationDao.selectList(queryWrapper);
        return categoryBrandRelationEntities;
    }
}