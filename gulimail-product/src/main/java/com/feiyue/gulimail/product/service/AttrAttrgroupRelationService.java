package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.feiyue.gulimail.product.vo.AttrGroupRelationVo;
import com.feiyue.gulimail.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;


public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttrGroupVo(List<AttrGroupRelationVo> attrGroupRelationVo);

    void deleteAttrGroupVo(List<AttrGroupRelationVo> attrGroupRelationVo);

}

