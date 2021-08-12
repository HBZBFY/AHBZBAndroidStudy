package com.feiyue.gulimail.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "name無法為空")
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "必須為URL")
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
//	@Pattern()

	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@Pattern(regexp = "/^[a-zA-Z]$/",message = "檢索必須為字母")
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(0)
	private Integer sort;

}
