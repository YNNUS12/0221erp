package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.Dept;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
    
    /**
     * 查询部门
     */
    List<Dept> queryAllDept(Dept dept);

    /**
     * 加载最大的排序码
     * @return
     */
	Integer queryDeptMaxOrderNum();

	/**
	 * 根据部门ID查询子节点的数量
	 * @param id
	 * @return
	 */
	Integer queryChildrenCountByDeptId(Integer id);
}