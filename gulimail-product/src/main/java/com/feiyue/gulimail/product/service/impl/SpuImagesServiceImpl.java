package com.feiyue.gulimail.product.service.impl;

import com.feiyue.gulimail.product.dao.SpuImagesDao;
import com.feiyue.gulimail.product.entity.SpuImagesEntity;
import com.feiyue.gulimail.product.service.SpuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("spuImagesService")
public class SpuImagesServiceImpl extends ServiceImpl<SpuImagesDao, SpuImagesEntity> implements SpuImagesService {


    @Override
    public void saveImages(List<String> images, Long id) {

        if (images != null && !images.isEmpty()) {
            images.stream().forEach(item -> {
                SpuImagesEntity spuImagesEntity = new SpuImagesEntity();
                spuImagesEntity.setId(id);
                spuImagesEntity.setImgUrl(item);
                this.save(spuImagesEntity);
            });
        }
    }
}