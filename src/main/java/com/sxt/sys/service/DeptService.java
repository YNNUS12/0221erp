package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Dept;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.DeptVo;

/**
 * 部门管理数据接口
 * @author LJH
 *
 */
public interface DeptService {

	
	/**
	 * 查询所有部门 返回List
	 */
	public List<Dept> queryAllDeptForList(DeptVo deptVo);
	
	/**
	 * 分页查询部门
	 */
	public DataGridView queryAllDept(DeptVo deptVo);

	/**
	 * 加载最大的排序码
	 * @return
	 */
	public Integer queryDeptMaxOrderNum();
	
	/**
	 * 添加部门
	 * @param deptVo
	 */
	public void addDept(DeptVo deptVo);

	/**
	 * 修改部门
	 * @param deptVo
	 */
	public void updateDept(DeptVo deptVo);

	/**
	 * 根据部门ID查询子节点的数量
	 * @param id
	 * @return
	 */
	public Integer queryChildrenCountByDeptId(Integer id);

	/**
	 * 删除部门
	 * @param id
	 */
	public void deleteDept(Integer id);
	
	
	
}
