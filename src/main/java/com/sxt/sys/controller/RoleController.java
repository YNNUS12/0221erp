package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.PermissionVo;
import com.sxt.sys.vo.RoleVo;

@RestController
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;


	/**
	 * 加载角色列的数据
	 */
	@RequestMapping("loadAllRole")
	public DataGridView loadAllRole(RoleVo roleVo) {
		return this.roleService.queryAllRole(roleVo);
	}

	
	
	
	/**
	 * 添加角色
	 */
	@RequestMapping("addRole")
	public ResultObj addRole(RoleVo roleVo) {
		try {
			roleVo.setCreatetime(new Date());
			this.roleService.addRole(roleVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("updateRole")
	public ResultObj updateRole(RoleVo roleVo) {
		try {
			this.roleService.updateRole(roleVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 删除角色
	 * 
	 */
	@RequestMapping("deleteRole")
	public ResultObj deleteRole(RoleVo roleVo) {
		try {
			this.roleService.deleteRole(roleVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	
	/**
	 * 根据角色ID加载分配权限树的数据
	 */
	@RequestMapping("loadSelectPermission")
	public DataGridView loadSelectPermission(RoleVo roleVo) {
		//查询所有的菜单和权限
		PermissionVo permissionVo=new PermissionVo();
		permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		List<Permission> allPermissionlist = permissionService.queryAllPermissionForList(permissionVo);
		//根据角色ID查询当前角色拥有的权限和菜单
		List<Permission> curretRoleList=this.permissionService.queryPermissionByRoldIdForList(roleVo.getId());
		
		List<DTreeNode> treeNodes=new ArrayList<>();
		
		for (Permission p1 : allPermissionlist) {
			Map<String,Object> checkArr=new HashMap<>();
			Integer checked=0;
			for (Permission p2 : curretRoleList) {
				if(p1.getId()==p2.getId()) {
					checked=1;
					break;
				}
			}
			checkArr.put("checked", checked);
			treeNodes.add(new DTreeNode(p1.getId(), p1.getPid(), p1.getName(),checkArr));
		}
		return new DataGridView(treeNodes);
	}
	
	
	/**
	 * 保存角色和权限的关系
	 */
	@RequestMapping("saveRolePermission")
	public ResultObj saveRolePermission(RoleVo roleVo)
	{
		try {
			this.roleService.saveRolePermission(roleVo);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}
	
}
