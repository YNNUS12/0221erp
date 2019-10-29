package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * layui的树形式类
 * 
 * @author LJH
 *
 */
@Data
public class TreeNode {
	
	private Integer id;
	private Integer pid;
	private String title;
	private String icon;
	private String href;
	private Boolean spread;
	private List<TreeNode> children=new ArrayList<>();
	public TreeNode() {
	}
	public TreeNode(Integer id, Integer pid, String title, String icon, String href, Boolean spread) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.icon = icon;
		this.href = href;
		this.spread = spread;
	}
	
}
