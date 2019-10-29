package com.sxt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Provider;
import com.sxt.bus.mapper.ProviderMapper;
import com.sxt.bus.service.ProviderService;
import com.sxt.bus.vo.ProviderVo;
import com.sxt.sys.utils.DataGridView;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService{
	
	@Autowired
	private ProviderMapper providerMapper;

	@Transactional(readOnly=true)
	@Override
	public DataGridView queryAllProvider(ProviderVo providerVo) {
		Page<Object> page=PageHelper.startPage(providerVo.getPage(), providerVo.getLimit());
		List<Provider> data = this.providerMapper.queryAllProvider(providerVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addProvider(ProviderVo providerVo) {
		this.providerMapper.insertSelective(providerVo);
	}

	@Override
	public void updateProvider(ProviderVo providerVo) {
		this.providerMapper.updateByPrimaryKeySelective(providerVo);
	}

	@Override
	public void deleteProvider(Integer id) {
		this.providerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Provider> queryAllProviderForList(ProviderVo providerVo) {
		return this.providerMapper.queryAllProvider(providerVo);
	}
	
	
	
	

}
