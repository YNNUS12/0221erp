package com.sxt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.LogInfoVo;

@RestController
@RequestMapping("logInfo")
public class LogInfoController {

	@Autowired
	private LogInfoService logInfoService;
	
	/**
	 * 查询
	 */
	@RequestMapping("loadAllLogInfo")
	public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {
		return this.logInfoService.queryAllLogInfo(logInfoVo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("deleteLogInfo")
	public ResultObj deleteLogInfo(LogInfoVo logInfoVo) {
		try {
			this.logInfoService.deleteLogInfo(logInfoVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
		}
}
