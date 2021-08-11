package com.feiyue.gulimail.product.service.impl;

import com.feiyue.gulimail.product.dao.AttrAttrgroupRelationDao;
import com.feiyue.gulimail.product.entity.AttrAttrgroupRelationEntity;
import com.feiyue.gulimail.product.service.AttrAttrgroupRelationService;
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

}