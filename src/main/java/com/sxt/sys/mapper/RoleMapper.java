package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.sys.domain.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    /**
     * 全查询
     */
    List<Role> queryAllRole(Role role);
    
    /**
     * 根据角色ID删除sys_role_permission
     */
    void deleteRolePermissionByRoleId(Integer roleId);
    
    /**
     * 根据角色ID删除sys_role_user
     */
    void deleteRoleUserByRoleId(Integer roleId);

    /**
     * 保存角色和权限的关系
     * @param roleId
     * @param pid
     */
	void insertRolePermission(@Param("rid")Integer roleId, @Param("pid")Integer pid);

	/**
	 * 根据用户ID查询角色
	 * @param available是否要用
	 * @param userid用户编号
	 * @return
	 */
	List<Role> queryRoleByUserId(@Param("available")Integer available, @Param("userid")Integer userid);
}