package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.utils.DTreeNode;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.DeptVo;

@RestController
@RequestMapping("dept")
public class DeptController {

	@Autowired
	private DeptService deptService;

	/**
	 * 加载树的数据
	 * 
	 * @param deptVo
	 * @return
	 */
	@RequestMapping("loadDeptTreeJson")
	public DataGridView loadDeptTreeJson(DeptVo deptVo) {
		deptVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		List<Dept> list = this.deptService.queryAllDeptForList(deptVo);
		List<DTreeNode> nodes = new ArrayList<>();

		for (Dept dept : list) {
			nodes.add(new DTreeNode(dept.getId(), dept.getPid(), dept.getName()));
		}
		return new DataGridView(nodes);
	}

	/**
	 * 加载部门列的数据
	 */
	@RequestMapping("loadAllDept")
	public DataGridView loadAllDept(DeptVo deptVo) {
		return this.deptService.queryAllDept(deptVo);
	}

	
	/**
	 * 加载最大的排序码+1
	 */
	@RequestMapping("loadDeptMaxOrderNum")
	public Map<String,Object> loadDeptMaxOrderNum(){
		Map<String,Object> map=new HashMap<>();
		Integer maxValue=this.deptService.queryDeptMaxOrderNum();
		map.put("value", maxValue+1);
		return map;
	}
	
	
	/**
	 * 添加部门
	 */
	@RequestMapping("addDept")
	public ResultObj addDept(DeptVo deptVo) {
		try {
			deptVo.setCreatetime(new Date());
			this.deptService.addDept(deptVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	/**
	 * 修改部门
	 */
	@RequestMapping("updateDept")
	public ResultObj updateDept(DeptVo deptVo) {
		try {
			this.deptService.updateDept(deptVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 查询当前部门有没有子节点   有返回code>0  否则code<0
	 */
	@RequestMapping("checkCurrentDeptHasChildren")
	public Map<String,Object> checkCurrentDeptHasChildren(DeptVo deptVo){
		Map<String,Object> map=new HashMap<>();
		Integer count=this.deptService.queryChildrenCountByDeptId(deptVo.getId());
		Integer value=count>0?1:-1;
		map.put("code", value);
		return map;
	}
	
	
	/**
	 * 删除部门
	 * 
	 */
	@RequestMapping("deleteDept")
	public ResultObj deleteDept(DeptVo deptVo) {
		try {
			this.deptService.deleteDept(deptVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	
}
