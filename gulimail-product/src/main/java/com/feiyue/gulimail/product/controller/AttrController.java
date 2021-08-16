package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.service.AttrService;
import com.feiyue.gulimail.product.vo.AttrRespVo;
import com.feiyue.gulimail.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attrVo) {
        attrService.saveVo(attrVo);
        return R.ok();
    }

    @GetMapping("/base/list/{catelogId}")
    public R getList(@RequestParam Map<String,Object> map, @PathVariable("carelogId") Long catelogId) {
        PageUtils page = attrService.queryBaseAttrPage(map, catelogId);
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo attrRespVo = attrService.getAttrInfo(attrId);
        return R.ok().put("data", null);
    }

    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attrVo) {
        attrService.updateAttr(attrVo);
        return R.ok();
    }

}
