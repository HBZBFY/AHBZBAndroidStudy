package com.feiyue.gulimail.gulimailware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.gulimailware.entity.WareInfoEntity;

import java.util.Map;

public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> parm);
}

