package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;


public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils querypage(Map<String, Object> parm);

    void updateBrandInfo(Long brandId, String name);

    void updateBrandInfoByCatelog(Long catId, String name);

    List<CategoryBrandRelationEntity> getBrandList(Long catId);

    List<CategoryBrandRelationEntity> getCatelogList(Long brandId);
}

