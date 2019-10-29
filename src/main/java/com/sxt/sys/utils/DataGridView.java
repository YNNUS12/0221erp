package com.sxt.sys.utils;

import java.util.List;

import lombok.Data;

@Data
public class DataGridView {

	private Integer code = 0;
	private String msg = "";
	private Long count;
	private List<?> data;

	public DataGridView(Long count, List<?> data) {
		super();
		this.count = count;
		this.data = data;
	}
	public DataGridView( List<?> data) {
		super();
		this.data = data;
	}

	public DataGridView() {
	}

}
