package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.LogInfo;
import com.sxt.sys.mapper.LogInfoMapper;
import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;

@Service
@Transactional
public class LogInfoServiceImpl implements LogInfoService {

	@Autowired
	private LogInfoMapper logInfoMapper;

	@Override
	@Transactional(readOnly=true)
	public DataGridView queryAllLogInfo(LogInfoVo infoVo) {
		Page<Object> page = PageHelper.startPage(infoVo.getPage(), infoVo.getLimit());
		List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(infoVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addLogInfo(LogInfoVo infoVo) {
		this.logInfoMapper.insertSelective(infoVo);
	}

	@Override
	public void deleteLogInfo(Integer id) {
		this.logInfoMapper.deleteByPrimaryKey(id);
	}

}
