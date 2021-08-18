package com.feiyue.gulimail.gulimailware.controller;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.gulimailware.service.PurchaseService;
import com.feiyue.gulimail.gulimailware.vo.MergeVo;
import com.feiyue.gulimail.gulimailware.vo.PurchaseDoneVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/merge")
    public R merge(@RequestBody MergeVo mergeVo) {
        purchaseService.mergePurchase(mergeVo);
        return R.ok();
    }

    @GetMapping("/unreceive/list")
    public R getUnreceiveList() {
        PageUtils page = purchaseService.getUnreceiveList();
        return R.ok().put("data", page);
    }

    @PostMapping("/received")
    public R received(@RequestBody Long[] ids) {
        purchaseService.received(ids);
        return R.ok();
    }

    @PostMapping("/done")
    public R done(@RequestBody PurchaseDoneVo purchaseDoneVo) {
        purchaseService.done(purchaseDoneVo);
        return R.ok();
    }
}
