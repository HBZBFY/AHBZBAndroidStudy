package com.feiyue.gulimail.gulimailmember.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.gulimailmember.dao.MemberLevelDao;
import com.feiyue.gulimail.gulimailmember.entity.MemberLevelEntity;
import com.feiyue.gulimail.gulimailmember.service.MemberLevelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Override
    public PageUtils pagelist(Map<String, Object> parm) {
        QueryWrapper<MemberLevelEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) parm.get("key");
        if (!StringUtils.isBlank(key)) {
            queryWrapper.eq("name", key).or().like("name", key);
        }
        IPage<MemberLevelEntity> page = this.page(new Query<MemberLevelEntity>().getPage(parm), queryWrapper);
        return new PageUtils(page);
    }
}