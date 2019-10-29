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
import com.sxt.sys.domain.User;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.utils.TreeNodeBuilder;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.PermissionVo;

/**
 * 菜单管理
 * 
 * @author LJH
 *
 */
@RestController
@RequestMapping("menu")
public class MenuController {
	
	@Autowired
	private PermissionService permissionService;

	
	/***   主页导航使用的 开始  */
	/**
	 * 加载首页左边的导航菜单树
	 */
	@RequestMapping("loadIndexLeftMenuJson")
	public List<TreeNode> loadIndexLeftMenuJson(PermissionVo permissionVo) {
		User user=WebUtils.getCurrentUser();
		List<Permission> list=null;
		permissionVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		permissionVo.setType(SysConstast.PERMISSION_TYPE_MENU);
		if(user.getType()==SysConstast.USER_TYPE_SUPER) {
			list= permissionService.queryAllPermissionForList(permissionVo);
		}else {
			list=permissionService.queryPermissionByUserIdForList(permissionVo,user.getId());
		}
		List<TreeNode> treeNodes=new ArrayList<>();
		for (Permission p : list) {
			Integer id=p.getId();
			Integer pid=p.getPid();
			String title=p.getName();
			String icon=p.getIcon();
			String href=p.getHref();
			Boolean spread=p.getOpen()==SysConstast.OPEN_TRUE?true:false;
			treeNodes.add(new TreeNode(id, pid, title, icon, href, spread));
		}
		
		return TreeNodeBuilder.builder(treeNodes, 1);
	}
	
	/***   主页导航使用的结束   */
	
	
	/**************菜单管理开始***************/
	/**
	 * 加载树的数据
	 * 
	 * @param permissionVo
	 * @return
	 */
	@RequestMapping("loadMenuTreeJson")
	public DataGridView loadMenuTreeJson(PermissionVo permissionVo) {
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
	 * 加载菜单列的数据
	 */
	@RequestMapping("loadAllMenu")
	public DataGridView loadAllMenu(PermissionVo permissionVo) {
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
	 * 添加菜单
	 */
	@RequestMapping("addMenu")
	public ResultObj addMenus(PermissionVo permissionVo) {
		try {
			//设置默认值
			permissionVo.setType(SysConstast.PERMISSION_TYPE_MENU);
			this.permissionService.addPermission(permissionVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改菜单
	 */
	@RequestMapping("updateMenu")
	public ResultObj updateMenu(PermissionVo permissionVo) {
		try {
			this.permissionService.updatePermission(permissionVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 查询当前菜单有没有子节点   有返回code>0  否则code<0
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
	 * 删除菜单
	 * 
	 */
	@RequestMapping("deleteMenu")
	public ResultObj deleteMenu(PermissionVo permissionVo) {
		try {
			this.permissionService.deletePermission(permissionVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**************菜单管理结束***************/
}
