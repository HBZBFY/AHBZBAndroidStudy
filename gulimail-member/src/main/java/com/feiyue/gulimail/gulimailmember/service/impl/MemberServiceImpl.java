package com.feiyue.gulimail.gulimailmember.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.gulimailmember.dao.MemberDao;
import com.feiyue.gulimail.gulimailmember.entity.MemberEntity;
import com.feiyue.gulimail.gulimailmember.service.MemberService;
import org.springframework.stereotype.Service;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {


}