package com.sxt.bus.vo;

import com.sxt.bus.domain.Goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVo extends Goods {

	private Integer page; // 当前页

	private Integer limit;// pageSize
}
