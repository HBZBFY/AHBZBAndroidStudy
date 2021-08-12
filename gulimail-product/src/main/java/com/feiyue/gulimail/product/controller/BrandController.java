package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.BrandEntity;
import com.feiyue.gulimail.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/save")
    public R save(@Valid @RequestBody BrandEntity brandEntity) {
        brandService.save(brandEntity);
        return R.ok();
    }
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> parm) {
        PageUtils page = brandService.queryPage(parm);
        return R.ok().put("page", page);
    }
}
