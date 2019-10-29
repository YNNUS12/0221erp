package com.sxt.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务中转路由控制器
 * @author LJH
 *
 */
@Controller
@RequestMapping("bus")
public class BusController {

	/**
	 * 跳转到客户管理
	 */
	@RequestMapping("toCustomerManager")
	public String toCustumerManager() {
		return "business/customer/customerManager";
	}
	/**
	 * 跳转到供应商管理
	 */
	@RequestMapping("toProviderManager")
	public String toProviderManager() {
		return "business/provider/providerManager";
	}
	/**
	 * 跳转到商品管理
	 */
	@RequestMapping("toGoodsManager")
	public String toGoodsManager() {
		return "business/goods/goodsManager";
	}
	/**
	 * 跳转到商品进货管理
	 */
	@RequestMapping("toInportManager")
	public String toInportManager() {
		return "business/inport/inportManager";
	}
}
