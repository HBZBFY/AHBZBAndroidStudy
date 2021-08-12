package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.service.CategoryBrandRelationService;
import com.feiyue.gulimail.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("catelog/list")
    public R catelogList(@RequestParam Map<String, Object> parm) {
        PageUtils page = categoryBrandRelationService.querypage(parm);
        return R.ok().put("data", page);
    }
}
