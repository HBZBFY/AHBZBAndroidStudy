package com.feiyue.gulimail.product.service.impl;

import com.feiyue.gulimail.product.dao.CategoryDao;
import com.feiyue.gulimail.product.entity.CategoryEntity;
import com.feiyue.gulimail.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public List<CategoryEntity> listWithTree() {
        // 查到所有数据
        List<CategoryEntity> categoryList = baseMapper.selectList(null);

        // 获取一级分类
        List<CategoryEntity> collect = categoryList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(menu -> {
                    //获取子菜单
                    menu.setChildren(getChildrens(menu, categoryList));
                    return menu;
                })
                .sorted((o1, o2) -> (o1.getSort() == null ? 0: o1.getSort()) - (o2.getSort() == null ? 0: o2.getSort()))
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        Collections.reverse(path);
        return path.toArray(new Long[path.size()]);
    }

    private List<Long> finfParentPath(Long catelogId,  List<Long> path) {
        path.add(catelogId);
        CategoryEntity categoryEntity = this.getById(catelogId);
        if (categoryEntity != null && categoryEntity.getParentCid() != null) {
            finfParentPath(categoryEntity.getParentCid(), path);
        }
        return path;
    }

    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> collect = all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == root.getCatId())
                .map(menu -> {
                    //获取子菜单
                    menu.setChildren(getChildrens(menu, all));
                    return menu;
                })
                .sorted((o1, o2) -> (o1.getSort() == null ? 0: o1.getSort()) - (o2.getSort() == null ? 0: o2.getSort()))
                .collect(Collectors.toList());

        return collect;
    }
}