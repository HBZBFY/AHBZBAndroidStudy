package com.feiyue.gulimail.gulimailmember.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feiyue.gulimail.gulimailmember.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
