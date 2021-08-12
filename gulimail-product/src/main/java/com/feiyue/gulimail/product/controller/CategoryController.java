package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.CategoryEntity;
import com.feiyue.gulimail.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list/tree")
    public R listTree() {
        List<CategoryEntity> list = categoryService.listWithTree();
        return R.ok().put("data", list);
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds) {
        categoryService.removeByIds(Arrays.asList(catIds));
        return  R.ok();
    }

    @RequestMapping("save")
    public R save(@RequestBody CategoryEntity categoryEntity) {
        categoryService.save(categoryEntity);
        return  R.ok();
    }

    @RequestMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable Long id) {
        CategoryEntity categoryEntity = categoryService.getById(id);
        return R.ok().put("data", categoryEntity);
    }
}
