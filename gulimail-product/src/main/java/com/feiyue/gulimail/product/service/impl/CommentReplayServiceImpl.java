package com.feiyue.gulimail.product.service.impl;

import com.feiyue.gulimail.product.dao.CommentReplayDao;
import com.feiyue.gulimail.product.entity.CommentReplayEntity;
import com.feiyue.gulimail.product.service.CommentReplayService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {

}