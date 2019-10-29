package com.sxt.sys.vo;

import com.sxt.sys.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleVo extends Role{

	//分页属性
	private Integer page;
	private Integer limit;
	
	private Integer[] ids;
	
}
