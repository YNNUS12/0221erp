package com.sxt.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Permission;
import com.sxt.sys.mapper.PermissionMapper;
import com.sxt.sys.service.PermissionService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.PermissionVo;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	@Transactional(readOnly=true)
	public List<Permission> queryAllPermissionForList(PermissionVo permissionVo) {
		return permissionMapper.queryAllPermission(permissionVo);
	}

	@Override
	public List<Permission> queryPermissionByUserIdForList(PermissionVo permissionVo, Integer userId) {
		return permissionMapper.queryPermissionByUserId(permissionVo,userId);
	}

	@Override
	public DataGridView queryAllPermission(PermissionVo permissionVo) {
		Page<Object> page=PageHelper.startPage(permissionVo.getPage(), permissionVo.getLimit());
		List<Permission> data = this.permissionMapper.queryAllPermission(permissionVo);
		return new DataGridView(page.getTotal(), data);
	}
	@Override
	public Integer queryPermissionMaxOrderNum() {
		return this.permissionMapper.queryPermissionMaxOrderNum();
	}

	@Override
	public void addPermission(PermissionVo permissionVo) {
		this.permissionMapper.insertSelective(permissionVo);
	}

	@Override
	public void updatePermission(PermissionVo permissionVo) {
		this.permissionMapper.updateByPrimaryKeySelective(permissionVo);
	}

	@Override
	public Integer queryChildrenCountByPermissionId(Integer id) {
		return this.permissionMapper.queryChildrenCountByPermissionId(id);
	}

	@Override
	public void deletePermission(Integer id) {
		this.permissionMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据角色ID查询当前角色拥有的权限和菜单
	 */
	@Override
	public List<Permission> queryPermissionByRoldIdForList(Integer id) {
		return this.permissionMapper.queryPermissionByRoldId(id);
	}

	@Override
	public List<String> queryPermissionByUserIdForListString(PermissionVo permissionVo, Integer id) {
		List<Permission> list = this.permissionMapper.queryPermissionByUserId(permissionVo, id);
		
		List<String> permissions=new ArrayList<>();
		for (Permission permission : list) {
			permissions.add(permission.getPercode());
		}
		return permissions;
	}



}
