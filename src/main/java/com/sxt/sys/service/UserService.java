package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;

public interface UserService {
	/**
     * 根据用户名查询用户
     * @param loginName
     * @return
     */
    User queryUserByLoginName(String loginName);
    
    
    /**
	 * 分页查询用户
	 */
	public DataGridView queryAllUser(UserVo userVo);


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
	List<User> queryUsersByDeptId(Integer deptid);


	/**
	 * 添加用户
	 * @param userVo
	 */
	void addUser(UserVo userVo);


	/**
	 * 根据用户ID查询用户对象
	 * @param id
	 * @return
	 */
	User queryUserById(Integer id);

	/**
	 * 修改用户
	 * @param userVo
	 */
	void updateUser(UserVo userVo);
	

	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Integer id);


	/**
	 * 重置密码
	 * @param id
	 */
	void resetPwd(Integer id);

	/**
	 * 保存用户和角色之间的关系
	 * @param id
	 */
	void saveUserRole(UserVo userVo);
}
