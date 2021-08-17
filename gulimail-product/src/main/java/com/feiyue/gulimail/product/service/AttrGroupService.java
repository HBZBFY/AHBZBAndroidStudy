package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.AttrGroupEntity;
import com.feiyue.gulimail.product.vo.AttrGroupRelationVo;
import com.feiyue.gulimail.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> parms, Long id);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrs(Long catelogId);
}

