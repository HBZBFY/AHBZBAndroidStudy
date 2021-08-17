package com.feiyue.gulimail.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.feiyue.common.utils.PageUtils;
import com.feiyue.common.utils.Query;
import com.feiyue.gulimail.product.dao.AttrDao;
import com.feiyue.gulimail.product.dao.AttrGroupDao;
import com.feiyue.gulimail.product.entity.AttrEntity;
import com.feiyue.gulimail.product.entity.AttrGroupEntity;
import com.feiyue.gulimail.product.service.AttrGroupService;
import com.feiyue.gulimail.product.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

;import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    private AttrGroupDao attrGroupDao;

    @Autowired
    private AttrDao attrDao;

    @Override
    public PageUtils queryPage(Map<String, Object> parms, Long id) {
        String key = (String) parms.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if(!StringUtils.isEmpty(key)){
            wrapper.and((obj)->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }
        if(id == 0){
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(parms),
                    wrapper);
            return new PageUtils(page);
        }else {
            wrapper.eq("catelog_id",id);
            IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(parms),
                    wrapper);
            return new PageUtils(page);
        }
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrs(Long catelogId) {
        List<AttrGroupWithAttrsVo> list = new ArrayList<>();
        QueryWrapper<AttrGroupEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("catelog_id", catelogId);
        List<AttrGroupEntity> attrGroupEntities = attrGroupDao.selectList(queryWrapper);
        if (attrGroupEntities != null && !attrGroupEntities.isEmpty()) {
            list = attrGroupEntities.stream().map(item -> {
                AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
                BeanUtils.copyProperties(item, attrGroupWithAttrsVo);
                List<AttrEntity> attrs = attrDao.selectList(new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId));
                attrGroupWithAttrsVo.setAttrs(attrs);
                return attrGroupWithAttrsVo;
            }).collect(Collectors.toList());
        }
        return list;
    }
}