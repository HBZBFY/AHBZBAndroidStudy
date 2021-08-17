package com.feiyue.gulimail.product.service.impl;

import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.feiyue.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.feiyue.gulimail.product.service.AttrAttrgroupRelationService;
import com.feiyue.gulimail.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public void saveAttrGroupVo(List<AttrGroupRelationVo> attrGroupRelationVo) {
        if (attrGroupRelationVo != null && !attrGroupRelationVo.isEmpty()) {
            attrGroupRelationVo.forEach(item -> {
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                attrAttrgroupRelationEntity.setAttrId(item.getAttrId());
                attrAttrgroupRelationEntity.setAttrGroupId(item.getAttrGroupId());
                this.save(attrAttrgroupRelationEntity);
            });
        }
    }

    @Override
    public void deleteAttrGroupVo(List<AttrGroupRelationVo> attrGroupRelationVo) {
        if (attrGroupRelationVo != null && !attrGroupRelationVo.isEmpty()) {
            attrGroupRelationVo.forEach(item -> {
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                BeanUtils.copyProperties(item, attrAttrgroupRelationEntity);
                this.removeById(attrAttrgroupRelationEntity);
            });
        }
    }
}