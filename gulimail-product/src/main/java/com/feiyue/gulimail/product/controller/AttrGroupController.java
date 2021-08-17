package com.feiyue.gulimail.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.R;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.entity.AttrGroupEntity;
import com.feiyue.gulimail.product.service.AttrAttrgroupRelationService;
import com.feiyue.gulimail.product.service.AttrGroupService;
import com.feiyue.gulimail.product.service.AttrService;
import com.feiyue.gulimail.product.service.CategoryService;
import com.feiyue.gulimail.product.vo.AttrGroupRelationVo;
import com.feiyue.gulimail.product.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

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

    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> entities =  attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data",entities);
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody  AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }

    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageUtils page = attrService.getNoRelationAttr(params,attrgroupId);
        return R.ok().put("page",page);
    }

    @RequestMapping("attr/relation")
    public R saveAttrRelation(@RequestBody List<AttrGroupRelationVo> attrGroupRelationVo) {
        attrAttrgroupRelationService.saveAttrGroupVo(attrGroupRelationVo);
        return R.ok();
    }

    @RequestMapping("attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationVo> attrGroupRelationVo) {
        attrAttrgroupRelationService.deleteAttrGroupVo(attrGroupRelationVo);
        return R.ok();
    }

    @GetMapping("/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable Long catelogId) {
        List<AttrGroupWithAttrsVo> list = attrGroupService.getAttrGroupWithAttrs(catelogId);
        return R.ok().put("data", list);
    }

}
