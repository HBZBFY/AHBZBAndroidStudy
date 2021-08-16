package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.service.AttrService;
import com.feiyue.gulimail.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @RequestMapping("save")
    public R save(@RequestBody AttrVo attrVo) {
        attrService.saveVo(attrVo);
        return R.ok();
    }
}
