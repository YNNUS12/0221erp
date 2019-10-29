package com.sxt.sys.vo;

import java.util.Date;

import com.sxt.sys.domain.LogInfo;

public class LogInfoVo extends LogInfo{
	
	private Integer page;
	private Integer limit;
	
	private Date startDate;
	
	private Date endDate;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	

}
