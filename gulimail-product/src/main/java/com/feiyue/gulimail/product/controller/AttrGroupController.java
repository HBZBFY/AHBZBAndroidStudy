package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.AttrGroupEntity;
import com.feiyue.gulimail.product.service.AttrGroupService;
import com.feiyue.gulimail.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getAttrGroupByCategoryId/{id}")
    public R getAttrGroupByCategoryId(@RequestParam Map<String,Object> parms, @PathVariable("id") Long id) {
        PageUtils pages = attrGroupService.queryPage(parms, id);
        return  R.ok().put("data", pages);
    }
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long[] path = categoryService.findCatelogPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(path);
        return R.ok().put("data", attrGroup);
    }
}
