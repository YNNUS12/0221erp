package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.MD5Utils;
import com.sxt.sys.utils.PinyinUtils;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.RoleVo;
import com.sxt.sys.vo.UserVo;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	/**
	 * 加载角色列的数据
	 */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo userVo) {
		return this.userService.queryAllUser(userVo);
	}
	
	/**
	 * 加载最大的排序码
	 */
	@RequestMapping("loadUserMaxOrderNum")
	public Map<String,Object> loadUserMaxOrderNum(){
		Map<String,Object> map=new HashMap<>();
		Integer value=this.userService.queryUserMaxOrderNum();
		map.put("value", value+1);
		return map;
	}
	
	/**
	 * 根据部门编号查询当前部门下的在职的员工
	 */
	@RequestMapping("loadUsersByDeptId")
	public List<User> loadUsersByDeptId(UserVo userVo){
		return this.userService.queryUsersByDeptId(userVo.getDeptid());
	}
	
	/**
	 * 
	 */
	@RequestMapping("changeChineseToPinyin")
	public Map<String,Object> changeChineseToPinyin(String name){
		Map<String,Object> map=new HashMap<>();
		String value=PinyinUtils.toPinyin(name);
		map.put("value", value);
		return map;
	}
	

	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo userVo) {
		try {
			userVo.setType(SysConstast.USER_TYPE_NORMAL);
			userVo.setSalt(MD5Utils.createSalt());
			userVo.setPwd(MD5Utils.md5(SysConstast.PWD_DEFALUT, userVo.getSalt(), 2));
			this.userService.addUser(userVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 根据用户ID查询用户对象
	 */
	@RequestMapping("queryUserById")
	public User queryUserById(UserVo userVo) {
		return this.userService.queryUserById(userVo.getId());
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo) {
		try {
			this.userService.updateUser(userVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(UserVo userVo) {
		try {
			this.userService.deleteUser(userVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 重置用户密码
	 */
	@RequestMapping("resetPwd")
	public ResultObj resetPwd(UserVo userVo) {
		try {
			this.userService.resetPwd(userVo.getId());
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}

	/**
	 * 加载用户管理里面的分配角色的角色列表并选中已拥有的角色
	 */
	@RequestMapping("loadUserRole")
	public DataGridView loadUserRole(UserVo userVo) {
		//查询所有角色不分页
		RoleVo roleVo=new RoleVo();
		roleVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		List<Role> allRoleForList = this.roleService.queryAllRoleForList(roleVo);
		//根据用户ID查询角色
		List<Role> userIdForList = this.roleService.queryRoleByUserIdForList(roleVo, userVo.getId());
		
		List<Map<String,Object>> list=new ArrayList<>();
		
		for (Role r1 : allRoleForList) {
			Boolean LAY_CHECKED=false;
			for (Role r2 : userIdForList) {
				if(r1.getId()==r2.getId()) {
					LAY_CHECKED=true;
					break;
				}
			}
			Map<String,Object> maps=new HashMap<>();
			maps.put("id", r1.getId());
			maps.put("name", r1.getName());
			maps.put("remark", r1.getRemark());
			maps.put("LAY_CHECKED", LAY_CHECKED);
			list.add(maps);
		}
		return new DataGridView(list);
	}
	
	/**
	 * 保存用户和角色之间的关系
	 * 
	 */
	@RequestMapping("saveUserRole")
	public ResultObj saveUserRole(UserVo userVo){
		try {
			this.userService.saveUserRole(userVo);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}
}
