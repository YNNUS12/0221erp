package com.sxt.sys.utils;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * layui-Dtree的树形式类
 * 
 * @author LJH
 *
 */
@Data
public class DTreeNode {
	
	private Integer id;
	private Integer parentId;
	private String title;
	
	private Map<String,Object> checkArr=new HashMap<>();
	
	public DTreeNode(Integer id, Integer parentId, String title) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
	}

	public DTreeNode(Integer id, Integer parentId, String title, Map<String, Object> checkArr) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.title = title;
		this.checkArr = checkArr;
	}
}
