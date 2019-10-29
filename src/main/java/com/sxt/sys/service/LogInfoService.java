package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;

public interface LogInfoService {

	//查询
	DataGridView queryAllLogInfo(LogInfoVo infoVo);
	//添加
	void addLogInfo(LogInfoVo infoVo);
	//删除
	void deleteLogInfo(Integer id);
	
}
