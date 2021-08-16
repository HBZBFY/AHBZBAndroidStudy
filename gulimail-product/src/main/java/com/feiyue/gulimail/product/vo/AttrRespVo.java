package com.feiyue.gulimail.product.vo;

import lombok.Data;

@Data
public class AttrRespVo extends AttrVo{
    private String catelogName;
    private String grandName;
    private Long[] catelogPath;
}
