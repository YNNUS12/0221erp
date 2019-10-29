package com.sxt.bus.vo;

import com.sxt.bus.domain.Customer;

public class CustomerVo extends Customer {


	private Integer page; //当前页

	private Integer limit;//pageSize

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
