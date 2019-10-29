package com.sxt.bus.service;

import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

/**
 * 客户管理的服务接口
 * @author LJH
 *
 */
public interface CustomerService {
	/**
	 * 分页查询
	 */
	public DataGridView queryAllCustomer(CustomerVo customerVo);
	/**
	 * 添加客户
	 * @param customerVo
	 */
	public void addCustomer(CustomerVo customerVo);
	/**
	 * 修改客户
	 * @param customerVo
	 */
	public void updateCustomer(CustomerVo customerVo);
	/**
	 * 删除客户
	 * @param id
	 */
	public void deleteCustomer(Integer id);
}
