package com.sxt.bus.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sxt.bus.domain.Inport;
import com.sxt.bus.domain.Provider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InportVo extends Inport {

	private Integer page; // 当前页

	private Integer limit;// pageSize
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
}
