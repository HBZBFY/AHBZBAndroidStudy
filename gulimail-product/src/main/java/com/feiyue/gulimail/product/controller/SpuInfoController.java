package com.feiyue.gulimail.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.feiyue.common.to.es.SkuEsModel;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.service.SpuInfoService;
import com.feiyue.gulimail.product.vo.SpuSaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product/spuinfo")
public class SpuInfoController {

    @Autowired
    private SpuInfoService spuInfoService;

    @PostMapping("{spuId}/up")
    public R spuIdUp(@PathVariable("spuId") Long spuId) {
        spuInfoService.up(spuId);

        return R.ok();
    }

    @RequestMapping("/save")
    public R save(@RequestBody SpuSaveVo vo){
        spuInfoService.saveSpuInfo(vo);
        return R.ok();
    }

    @RequestMapping("list")
    public R list (@RequestParam Map<String, Object> param) {
        PageUtils page = spuInfoService.queryPage(param);
        return R.ok().put("data", page);
    }

}
