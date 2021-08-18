package com.feiyue.gulimail.gulimailware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.gulimailware.dao.WareInfoDao;
import com.feiyue.gulimail.gulimailware.entity.WareInfoEntity;
import com.feiyue.gulimail.gulimailware.service.WareInfoService;
import org.springframework.stereotype.Service;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {


}