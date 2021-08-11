package com.feiyue.gulimail.product.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.product.dao.CategoryBrandRelationDao;
import com.feiyue.gulimail.product.entity.CategoryBrandRelationEntity;
import com.feiyue.gulimail.product.service.CategoryBrandRelationService;
import org.springframework.stereotype.Service;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {


}