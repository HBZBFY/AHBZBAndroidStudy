package com.feiyue.gulimail.gulimailmember.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.gulimailmember.dao.IntegrationChangeHistoryDao;
import com.feiyue.gulimail.gulimailmember.entity.IntegrationChangeHistoryEntity;
import com.feiyue.gulimail.gulimailmember.service.IntegrationChangeHistoryService;
import org.springframework.stereotype.Service;


@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity> implements IntegrationChangeHistoryService {


}