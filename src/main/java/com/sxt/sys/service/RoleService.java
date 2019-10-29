package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Role;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;

/**
 * 角色管理数据接口
 * @author LJH
 *
 */
public interface RoleService {

	
	/**
	 * 查询所有角色 返回List
	 */
	public List<Role> queryAllRoleForList(RoleVo roleVo);
	
	/**
	 * 根据用户ID查询用户的角色
	 */
	public List<Role> queryRoleByUserIdForList(RoleVo roleVo,Integer userid);
	
	/**
	 * 分页查询角色
	 */
	public DataGridView queryAllRole(RoleVo roleVo);

	/**
	 * 添加角色
	 * @param roleVo
	 */
	public void addRole(RoleVo roleVo);

	/**
	 * 修改角色
	 * @param roleVo
	 */
	public void updateRole(RoleVo roleVo);

	/**
	 * 删除角色
	 * @param id
	 */
	public void deleteRole(Integer id);

	/**
	 * 保存角色和权限的关系
	 * @param roleVo
	 */
	public void saveRolePermission(RoleVo roleVo);
	
	/**
	 * 根据用户ID查询角色返回List<String>  角色名 
	 * @param roleVo
	 * @param id
	 * @return
	 */
	public List<String> queryRoleByUserIdForListString(RoleVo roleVo, Integer id);
	
	
	
}
