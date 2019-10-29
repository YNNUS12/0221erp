package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.sys.domain.Permission;
import com.sxt.sys.vo.PermissionVo;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    /**
     * 查询菜单或权限
     */
    List<Permission> queryAllPermission(Permission permission);

    /**
     * 查询排序码最大值
     * @return
     */
	Integer queryPermissionMaxOrderNum();

	/**
	 * 查询当前节点的子节点数量
	 * @param id
	 * @return
	 */
	Integer queryChildrenCountByPermissionId(Integer id);

	/**
	 * 根据角色ID查询当前角色拥有的权限和菜单
	 * @param id
	 * @return
	 */
	List<Permission> queryPermissionByRoldId(Integer id);
	
	/**
	 * 根据用户ID查询菜单或权限信息
	 * @param permissionVo
	 * @param userId
	 * @return
	 */
	List<Permission> queryPermissionByUserId(@Param("permission")PermissionVo permissionVo, @Param("userId")Integer userId);
}