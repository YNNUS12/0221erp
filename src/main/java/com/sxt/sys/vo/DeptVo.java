package com.sxt.sys.vo;

import com.sxt.sys.domain.Dept;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVo extends Dept{

	//分页属性
	private Integer page;
	private Integer limit;
	
}
