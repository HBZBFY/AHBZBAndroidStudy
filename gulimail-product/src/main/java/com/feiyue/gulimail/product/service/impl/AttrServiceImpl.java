package com.feiyue.gulimail.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feiyue.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.feiyue.gulimail.product.dao.AttrDao;
import com.feiyue.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.service.AttrAttrgroupRelationService;
import com.feiyue.gulimail.product.service.AttrService;
import com.feiyue.gulimail.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Override
    public void saveVo(AttrVo attrVo) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attrVo, attrEntity);
        this.save(attrEntity);
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        BeanUtils.copyProperties(attrVo, attrAttrgroupRelationEntity);
        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }
}