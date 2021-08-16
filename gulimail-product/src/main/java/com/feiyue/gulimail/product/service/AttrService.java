package com.feiyue.gulimail.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.vo.AttrGroupRelationVo;
import com.feiyue.gulimail.product.vo.AttrRespVo;
import com.feiyue.gulimail.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

public interface AttrService extends IService<AttrEntity> {

    void saveVo(AttrVo attrVo);

    PageUtils queryBaseAttrPage(Map<String, Object> map, Long catelogId);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attrVo);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);
}

