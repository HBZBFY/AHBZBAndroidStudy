package com.feiyue.gulimail.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feiyue.gulimail.product.entity.SpuImagesEntity;

import java.util.List;
import java.util.Map;

public interface SpuImagesService extends IService<SpuImagesEntity> {

    void saveImages(List<String> images, Long id);
}

