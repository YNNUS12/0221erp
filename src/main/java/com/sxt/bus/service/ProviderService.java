package com.sxt.bus.service;

import java.util.List;

import com.sxt.bus.domain.Provider;
import com.sxt.bus.vo.ProviderVo;
import com.sxt.sys.utils.DataGridView;

/**
 * 供应商管理的服务接口
 * @author LJH
 *
 */
public interface ProviderService {
	/**
	 * 分页查询
	 */
	public DataGridView queryAllProvider(ProviderVo providerVo);
	/**
	 * 添加供应商
	 * @param providerVo
	 */
	public void addProvider(ProviderVo providerVo);
	/**
	 * 修改供应商
	 * @param providerVo
	 */
	public void updateProvider(ProviderVo providerVo);
	/**
	 * 删除供应商
	 * @param id
	 */
	public void deleteProvider(Integer id);
	
	/**
	 * 查询所有供应商返回集合
	 * @param providerVo
	 * @return
	 */
	public List<Provider> queryAllProviderForList(ProviderVo providerVo);
}
