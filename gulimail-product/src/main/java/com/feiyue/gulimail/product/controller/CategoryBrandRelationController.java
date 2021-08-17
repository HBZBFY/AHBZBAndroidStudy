package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.CategoryBrandRelationEntity;
import com.feiyue.gulimail.product.service.CategoryBrandRelationService;
import com.feiyue.gulimail.product.service.CategoryService;
import com.feiyue.gulimail.product.vo.BrandVo;
import com.feiyue.gulimail.product.vo.CatelogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @GetMapping("/list")
    public R catelogList(@RequestParam Map<String, Object> parm) {
        PageUtils page = categoryBrandRelationService.querypage(parm);
        return R.ok().put("data", page);
    }

    @RequestMapping("/save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelationEntity) {
        categoryBrandRelationService.save(categoryBrandRelationEntity);
        return R.ok();
    }

    @GetMapping("/brands/list")
    public R brandList(@RequestParam Long catId) {
      List<CategoryBrandRelationEntity> list = categoryBrandRelationService.getBrandList(catId);
      if (list != null && !list.isEmpty()) {
          List<BrandVo> collect = list.stream().map(item -> {
              BrandVo brandVo = new BrandVo();
              brandVo.setBrandId(item.getBrandId());
              brandVo.setBrandName(item.getBrandName());
              return brandVo;
          }).collect(Collectors.toList());
          return R.ok().put("data", collect);
      }else {
          return R.ok().put("data", null);
      }
    }

    @GetMapping("")
    public R catelogList(@RequestParam Long brandId) {
        List<CategoryBrandRelationEntity> list = categoryBrandRelationService.getCatelogList(brandId);
        if (list != null && !list.isEmpty()) {
            List<CatelogVo> collect = list.stream().map(item -> {
                CatelogVo catelogVo = new CatelogVo();
                catelogVo.setCatelogId(item.getCatelogId());
                catelogVo.setCatelogName(item.getCatelogName());
                return catelogVo;
            }).collect(Collectors.toList());
            return R.ok().put("data", collect);
        } else {
            return R.ok().put("data", null);
        }
    }
}
