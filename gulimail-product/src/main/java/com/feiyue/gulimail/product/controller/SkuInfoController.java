package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.SkuInfoEntity;
import com.feiyue.gulimail.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("product/skuinfo")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;
    @RequestMapping("list")
    public R list(@RequestParam Map<String, Object> parma) {
        return R.ok();
    }

    @RequestMapping("/save")
    //@RequiresPermissions("product:skuinfo:save")
    public R save(@RequestBody SkuInfoEntity skuInfo){
        skuInfoService.save(skuInfo);
        return R.ok();
    }
}
