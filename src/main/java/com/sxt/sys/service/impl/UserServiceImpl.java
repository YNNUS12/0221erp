package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.MD5Utils;
import com.sxt.sys.vo.UserVo;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Transactional(readOnly=true)
	@Override
	public User queryUserByLoginName(String loginName) {
		return userMapper.queryUserByLoginName(loginName);
	}

	@Override
	public DataGridView queryAllUser(UserVo userVo) {
		Page<Object> page=PageHelper.startPage(userVo.getPage(), userVo.getLimit());
		List<User> data = this.userMapper.queryAllUser(userVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public Integer queryUserMaxOrderNum() {
		return this.userMapper.queryUserMaxOrderNum();
	}

	/**
	 * 根据部门编号查询当前部门下的在职的员工
	 */
	@Override
	public List<User> queryUsersByDeptId(Integer deptid) {
		return this.userMapper.querUserByDeptId(deptid);
		
	}

	@Override
	public void addUser(UserVo userVo) {
		this.userMapper.insertSelective(userVo);
	}

	@Override
	public User queryUserById(Integer id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(UserVo userVo) {
		this.userMapper.updateByPrimaryKeySelective(userVo);
	}

	@Override
	public void deleteUser(Integer id) {
		
		//删除sys_role_user里面的uid=id的数据
		this.userMapper.deleteUserRoleByUserId(id);
		
		this.userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void resetPwd(Integer id) {
		User user=new User();
		user.setId(id);
		user.setSalt(MD5Utils.createSalt());
		user.setPwd(MD5Utils.md5(SysConstast.PWD_DEFALUT, user.getSalt(), 2));
		this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void saveUserRole(UserVo userVo) {
		
		this.userMapper.deleteUserRoleByUserId(userVo.getId());
		
		Integer [] roles=userVo.getIds();
		
		if(null!=roles&&roles.length>0) {
			for (Integer rid : roles) {
				this.userMapper.insertUserRole(userVo.getId(),rid);
			}
		}
		
		
		
	}

}
