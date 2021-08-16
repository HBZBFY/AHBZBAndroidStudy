package com.feiyue.gulimail.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

public interface AttrService extends IService<AttrEntity> {

    void saveVo(AttrVo attrVo);
}

