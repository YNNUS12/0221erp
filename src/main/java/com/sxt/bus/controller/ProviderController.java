package com.sxt.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.bus.domain.Provider;
import com.sxt.bus.service.ProviderService;
import com.sxt.bus.vo.ProviderVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;

/**
 * 供应商管理控制器
 * @author LJH
 *
 */
@RestController
@RequestMapping("provider")
public class ProviderController {
	

	@Autowired
	private ProviderService providerService;
	
	
	/**
	 * 加载供应商管理列表
	 */
	@RequestMapping("loadAllProvider")
	public DataGridView loadAllProvider(ProviderVo providerVo) {
		return this.providerService.queryAllProvider(providerVo);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("addProvider")
	public ResultObj addProvider(ProviderVo providerVo) {
		try {
			this.providerService.addProvider(providerVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改
	 */
	@RequestMapping("updateProvider")
	public ResultObj updateProvider(ProviderVo providerVo) {
		try {
			this.providerService.updateProvider(providerVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("deleteProvider")
	public ResultObj deleteProvider(ProviderVo providerVo) {
		try {
			this.providerService.deleteProvider(providerVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 查询所有供应商返回集合
	 */
	@RequestMapping("loadAllAvailableProviderJson")
	public List<Provider> loadAllAvailableProviderJson(ProviderVo providerVo){
		 providerVo.setAvailable(SysConstast.AVAILABLE_TRUE);
		 return this.providerService.queryAllProviderForList(providerVo);
	}
}
