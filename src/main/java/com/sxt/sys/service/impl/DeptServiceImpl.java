package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.mapper.DeptMapper;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.DeptVo;

@Service
@Transactional
public class DeptServiceImpl implements DeptService{

	@Autowired
	private DeptMapper deptMapper;
	
	@Transactional(readOnly=true)
	@Override
	public List<Dept> queryAllDeptForList(DeptVo deptVo) {
		return deptMapper.queryAllDept(deptVo);
	}
	@Transactional(readOnly=true)
	@Override
	public DataGridView queryAllDept(DeptVo deptVo) {
		Page<Object> page=PageHelper.startPage(deptVo.getPage(), deptVo.getLimit());
		List<Dept> data = this.deptMapper.queryAllDept(deptVo);
		return new DataGridView(page.getTotal(), data);
	}

	/**
	 * 加载最大的排序码
	 */
	@Transactional(readOnly=true)
	@Override
	public Integer queryDeptMaxOrderNum() {
		return this.deptMapper.queryDeptMaxOrderNum();
	}
	/**
	 * 添加部门
	 */
	@Override
	public void addDept(DeptVo deptVo) {
		this.deptMapper.insertSelective(deptVo);
	}
	@Override
	public void updateDept(DeptVo deptVo) {
		this.deptMapper.updateByPrimaryKeySelective(deptVo);
	}

	@Transactional(readOnly=true)
	@Override
	public Integer queryChildrenCountByDeptId(Integer id) {
		return this.deptMapper.queryChildrenCountByDeptId(id);
	}

	@Override
	public void deleteDept(Integer id) {
		this.deptMapper.deleteByPrimaryKey(id);
	}

}
