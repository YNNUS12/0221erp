package com.sxt.sys.vo;

import com.sxt.sys.domain.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionVo extends Permission {
	//分页属性
		private Integer page;
		private Integer limit;
}
