package com.feiyue.gulimail.gulimailmember.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.gulimailmember.dao.MemberLoginLogDao;
import com.feiyue.gulimail.gulimailmember.entity.MemberLoginLogEntity;
import com.feiyue.gulimail.gulimailmember.service.MemberLoginLogService;
import org.springframework.stereotype.Service;


@Service("memberLoginLogService")
public class MemberLoginLogServiceImpl extends ServiceImpl<MemberLoginLogDao, MemberLoginLogEntity> implements MemberLoginLogService {


}