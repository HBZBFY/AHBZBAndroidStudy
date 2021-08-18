package com.feiyue.gulimail.gulimailmember.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.gulimail.gulimailmember.entity.MemberLevelEntity;

import java.util.Map;


public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils pagelist(Map<String, Object> parm);
}

