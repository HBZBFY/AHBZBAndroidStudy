package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.feiyue.gulimail.product.service.AttrAttrgroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("product/attrattrgrouprelation")
public class AttrAttrgroupRelationController {
    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrAttrgroupRelationService.queryPage(params);
        return R.ok().put("page", page);
    }

    @RequestMapping("/save")
    public R save(@RequestBody AttrAttrgroupRelationEntity attrAttrgroupRelationEntity) {
        attrAttrgroupRelationService.save(attrAttrgroupRelationEntity);
        return R.ok();
    }



}
