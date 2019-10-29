package com.sxt.sys.vo;

import com.sxt.sys.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends User{

	//分页属性
	private Integer page;
	private Integer limit;
	
	private Integer[] ids;
	
}
