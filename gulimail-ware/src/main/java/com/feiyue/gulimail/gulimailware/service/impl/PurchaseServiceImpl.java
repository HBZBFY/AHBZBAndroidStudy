package com.feiyue.gulimail.gulimailware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.gulimail.gulimailware.dao.PurchaseDao;
import com.feiyue.gulimail.gulimailware.entity.PurchaseEntity;
import com.feiyue.gulimail.gulimailware.service.PurchaseService;
import org.springframework.stereotype.Service;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {


}