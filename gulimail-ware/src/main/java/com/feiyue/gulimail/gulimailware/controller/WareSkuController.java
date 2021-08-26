package com.feiyue.gulimail.gulimailware.controller;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.common.vo.SkuHasStockVo;
import com.feiyue.gulimail.gulimailware.service.WareSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("ware/waresku")
public class WareSkuController {

    @Autowired
    private WareSkuService wareSkuService;

    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> parm) {
        PageUtils page = wareSkuService.queryPage(parm);
        return R.ok().put("data", page);
    }
    @PostMapping("/hasStock")
    public R getSkuHasStock(@RequestBody List<Long> skuIds) {
        List<SkuHasStockVo> list = wareSkuService.getSkusHasStock(skuIds);
        return R.ok().put("data", list);
    }
}
