package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.PermissionVo;

/**
 * 权限管理
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("permission")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	/***   主页导航使用的结束   */
	
	
	/**************权限管理开始***************/
	/**
	 * 加载树的数据
	 * 
	 * @param permissionVo
	 * @return
	 */
	@RequestMapping("loadPermissionTreeJson")
	public DataGridView loadPermissionTreeJson(PermissionVo permissionVo) {
		permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		permissionVo.setType(SysConstast.PERMISSION_TYPE_MENU);
		List<Permission> list = this.permissionService.queryAllPermissionForList(permissionVo);
		List<DTreeNode> nodes = new ArrayList<>();
		for (Permission permission : list) {
			nodes.add(new DTreeNode(permission.getId(), permission.getPid(), permission.getName()));
		}
		return new DataGridView(nodes);
	}

	/**
	 * 加载权限列的数据
	 */
	@RequestMapping("loadAllPermission")
	public DataGridView loadAllPermission(PermissionVo permissionVo) {
		permissionVo.setType(SysConstast.PERMISSION_TYPE_PERMISSION);//
		return this.permissionService.queryAllPermission(permissionVo);
	}

	
	/**
	 * 加载最大的排序码+1
	 */
	@RequestMapping("loadPermissionMaxOrderNum")
	public Map<String,Object> loadPermissionMaxOrderNum(){
		Map<String,Object> map=new HashMap<>();
		Integer maxValue=this.permissionService.queryPermissionMaxOrderNum();
		map.put("value", maxValue+1);
		return map;
	}
	
	
	/**
	 * 添加权限
	 */
	@RequestMapping("addPermission")
	public ResultObj addPermissions(PermissionVo permissionVo) {
		try {
			//设置默认值
			permissionVo.setType(SysConstast.PERMISSION_TYPE_PERMISSION);
			this.permissionService.addPermission(permissionVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改权限
	 */
	@RequestMapping("updatePermission")
	public ResultObj updatePermission(PermissionVo permissionVo) {
		try {
			this.permissionService.updatePermission(permissionVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 查询当前权限有没有子节点   有返回code>0  否则code<0
	 */
	@RequestMapping("checkCurrentPermissionHasChildren")
	public Map<String,Object> checkCurrentPermissionHasChildren(PermissionVo permissionVo){
		Map<String,Object> map=new HashMap<>();
		Integer count=this.permissionService.queryChildrenCountByPermissionId(permissionVo.getId());
		Integer value=count>0?1:-1;
		map.put("code", value);
		return map;
	}
	
	
	/**
	 * 删除权限
	 * 
	 */
	@RequestMapping("deletePermission")
	public ResultObj deletePermission(PermissionVo permissionVo) {
		try {
			this.permissionService.deletePermission(permissionVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**************权限管理结束***************/
	
	

}
