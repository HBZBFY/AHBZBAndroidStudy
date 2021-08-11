package com.feiyue.gulimail.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.feiyue.gulimail.product.dao.AttrDao;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.service.AttrService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {


}