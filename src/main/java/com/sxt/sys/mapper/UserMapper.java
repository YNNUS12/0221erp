package com.sxt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.sys.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    User queryUserByLoginName(String loginName);
    
    
    /**
     * 全查询
     */
    List<User>  queryAllUser(User user);

    /**
     * 加载最大的排序码
     * @return
     */
	Integer queryUserMaxOrderNum();
	/**
	 * 根据部门编号查询当前部门下的在职的员工
	 * @param deptid
	 * @return
	 */
	List<User> querUserByDeptId(Integer deptid);

	/**
	 * 根据uid删除sys_role_user里面的数据
	 * @param id
	 */
	void deleteUserRoleByUserId(Integer id);

	/**
	 * 保存用户和角色的关系
	 * @param id
	 * @param rid
	 */
	void insertUserRole(@Param("uid")Integer id, @Param("rid")Integer rid);
    
}