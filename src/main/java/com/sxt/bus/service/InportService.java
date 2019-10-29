package com.sxt.bus.service;

import com.sxt.bus.vo.InportVo;
import com.sxt.sys.utils.DataGridView;

/**
 * 进货管理的服务接口
 * @author LJH
 *
 */
public interface InportService {
	/**
	 * 分页查询
	 */
	public DataGridView queryAllInport(InportVo inportVo);
	/**
	 * 添加进货
	 * @param inportVo
	 */
	public void addInport(InportVo inportVo);
	/**
	 * 修改进货
	 * @param inportVo
	 */
	public void updateInport(InportVo inportVo);
	/**
	 * 删除进货
	 * @param id
	 */
	public void deleteInport(InportVo inportVo);
}
