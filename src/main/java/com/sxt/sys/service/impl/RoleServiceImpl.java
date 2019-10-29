package com.sxt.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Transactional(readOnly = true)
	@Override
	public List<Role> queryAllRoleForList(RoleVo roleVo) {
		return roleMapper.queryAllRole(roleVo);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userid) {
		return this.roleMapper.queryRoleByUserId(roleVo.getAvailable(),userid);
	}

	@Transactional(readOnly = true)
	@Override
	public DataGridView queryAllRole(RoleVo roleVo) {
		Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
		List<Role> data = this.roleMapper.queryAllRole(roleVo);
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 添加角色
	 */
	@Override
	public void addRole(RoleVo roleVo) {
		this.roleMapper.insertSelective(roleVo);
	}

	@Override
	public void updateRole(RoleVo roleVo) {
		this.roleMapper.updateByPrimaryKeySelective(roleVo);
	}

	@Override
	public void deleteRole(Integer id) {
		//根据角色ID删除sys_role_permission
		this.roleMapper.deleteRolePermissionByRoleId(id);
		//根据角色ID删除sys_role_user
		this.roleMapper.deleteRoleUserByRoleId(id);
		this.roleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 保存角色和权限的关系
	 */
	@Override
	public void saveRolePermission(RoleVo roleVo) {
		Integer roleId=roleVo.getId();
		Integer[] ids=roleVo.getIds();
		//根据角色ID删除sys_role_permission的数据
		this.roleMapper.deleteRolePermissionByRoleId(roleId);
		
		if(null!=ids&&ids.length>0) {
			for (Integer pid : ids) {
				this.roleMapper.insertRolePermission(roleId,pid);
			}
		}
	}

	@Override
	public List<String> queryRoleByUserIdForListString(RoleVo roleVo, Integer id) {
		
		List<Role> list = this.roleMapper.queryRoleByUserId(roleVo.getAvailable(), id);
		List<String> roles=new ArrayList<>();
		for (Role role : list) {
			roles.add(role.getName());
		}
		return roles;
	}

}
