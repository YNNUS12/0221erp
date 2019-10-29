package com.sxt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统管理里面页面跳转
 * @author LJH
 *
 */
@Controller
@RequestMapping("sys")
public class SysController {
	
	/**
	 * 跳转到工作台的页面
	 */
	@RequestMapping("toDeskManager")
	public String toDeskManager() {
		return "system/deskManager";
	}
	
	
	/**
	 * 跳转到部门管理的页面
	 */
	@RequestMapping("toDeptManager")
	public String toDeptManager() {
		return "system/dept/deptManager";
	}
	/**
	 * 跳转到部门管理左边的页面
	 */
	@RequestMapping("toDeptLeft")
	public String toDeptLeft() {
		return "system/dept/deptLeft";
	}
	/**
	 * 跳转到部门管理右边的页面
	 */
	@RequestMapping("toDeptRight")
	public String toDeptRight() {
		return "system/dept/deptRight";
	}
	
	
	/**
	 * 跳转到菜单管理的页面
	 */
	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menu/menuManager";
	}
	/**
	 * 跳转到菜单管理左边的页面
	 */
	@RequestMapping("toMenuLeft")
	public String toMenuLeft() {
		return "system/menu/menuLeft";
	}
	/**
	 * 跳转到菜单管理右边的页面
	 */
	@RequestMapping("toMenuRight")
	public String toMenuRight() {
		return "system/menu/menuRight";
	}
	
	/**
	 * 跳转到权限管理的页面
	 */
	@RequestMapping("toPermissionManager")
	public String toPermissionManager() {
		return "system/permission/permissionManager";
	}
	/**
	 * 跳转到权限管理左边的页面
	 */
	@RequestMapping("toPermissionLeft")
	public String toPermissionLeft() {
		return "system/permission/permissionLeft";
	}
	/**
	 * 跳转到权限管理右边的页面
	 */
	@RequestMapping("toPermissionRight")
	public String toPermissionRight() {
		return "system/permission/permissionRight";
	}
	
	/**
	 * 跳转到角色管理的页面
	 */
	@RequestMapping("toRoleManager")
	public String toRoleManager() {
		return "system/role/roleManager";
	}
	
	
	
	/**
	 * 跳转到用户管理的页面
	 */
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "system/user/userManager";
	}
	/**
	 * 跳转到用户管理左边的页面
	 */
	@RequestMapping("toUserLeft")
	public String toUserLeft() {
		return "system/user/userLeft";
	}
	/**
	 * 跳转到用户管理右边的页面
	 */
	@RequestMapping("toUserRight")
	public String toUserRight() {
		return "system/user/userRight";
	}
	
	
	/**
	 * 跳转到日志管理
	 */
	@RequestMapping("toLogInfoManager")
	public String toLogInfoManager() {
		return "system/logInfo/logInfoManager";
	}
	/**
	 * 跳转到系统公告管理页面
	 */
	@RequestMapping("toNoticeManager")
	public String toNoticeManager() {
		return "system/notice/noticeManager";
	}
	

}
