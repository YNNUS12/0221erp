package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Permission;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.PermissionVo;

/**
 * 权限或菜单服务接口
 * @author LJH
 *
 */
public interface PermissionService {
	/**
	 * 查询菜单或权限返回List
	 * 
	 */
	public List<Permission> queryAllPermissionForList(PermissionVo permissionVo);

	/**
	 * 根据用户ID查询权限或菜单
	 * @param permissionVo
	 * @param userId
	 * @return
	 */
	public List<Permission> queryPermissionByUserIdForList(PermissionVo permissionVo, Integer userId);
	
	
	/**
	 * 查询菜单或权限 
	 * @param permissionVo
	 * @return
	 */
	public DataGridView queryAllPermission(PermissionVo permissionVo);
	
	/**
	 * 加载最大的排序码
	 * @return
	 */
	public Integer queryPermissionMaxOrderNum();
	
	/**
	 * 添加权限或菜单
	 * @param permissionVo
	 */
	public void addPermission(PermissionVo permissionVo);

	/**
	 * 修改权限或菜单
	 * @param permissionVo
	 */
	public void updatePermission(PermissionVo ccVo);

	/**
	 * 根据权限或菜单ID查询子节点的数量
	 * @param id
	 * @return
	 */
	public Integer queryChildrenCountByPermissionId(Integer id);

	/**
	 * 删除权限或菜单
	 * @param id
	 */
	public void deletePermission(Integer id);

	/**
	 * 根据角色ID查询当前角色拥有的权限和菜单
	 * @param id
	 * @return
	 */
	public List<Permission> queryPermissionByRoldIdForList(Integer id);

	/**
	 * 根据用户ID查询权限或菜单返回List<权限编码>
	 * @param permissionVo
	 * @param id
	 * @return
	 */
	public List<String> queryPermissionByUserIdForListString(PermissionVo permissionVo, Integer id);
}
