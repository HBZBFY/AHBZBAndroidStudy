package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.gulimail.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;
public interface CategoryService extends IService<CategoryEntity> {


    List<CategoryEntity> listWithTree();

    Long[] findCatelogPath(Long catelogId);

    void updateAll(CategoryEntity categoryEntity);
}

