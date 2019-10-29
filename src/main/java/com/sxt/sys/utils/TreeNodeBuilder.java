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
public class TreeNodeBuilder {
	
	/**
	 * 把简单的集合数据转成标准的有层级关系的对象数据
	 * @param treeNodes
	 * @param topId
	 * @return
	 */
	public static List<TreeNode> builder(List<TreeNode> treeNodes,Integer topPid){
		List<TreeNode> nodes=new ArrayList<>();
		
		for (TreeNode n1 : treeNodes) {
			if(n1.getPid()==topPid) {
				nodes.add(n1);
			}
			for (TreeNode n2 : treeNodes) {
				if(n2.getPid()==n1.getId()) {
					n1.getChildren().add(n2);
				}
			}
		}
		return nodes;
	}
	
}
