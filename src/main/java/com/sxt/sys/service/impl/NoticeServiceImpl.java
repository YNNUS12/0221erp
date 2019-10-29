package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Notice;
import com.sxt.sys.mapper.NoticeMapper;
import com.sxt.sys.service.NoticeService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NoticeVo;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Transactional(readOnly=true)
	@Override
	public DataGridView queryAllNotices(NoticeVo noticeVo) {
		Page<Object> page=PageHelper.startPage(noticeVo.getPage(), noticeVo.getLimit());
		List<Notice> data=this.noticeMapper.queryAllNotice(noticeVo);
		DataGridView view=new DataGridView(page.getTotal(), data);
		return view;
	}

	/**
	 * 添加公告
	 */
	@Override
	public void addNotice(NoticeVo noticeVo) {
		this.noticeMapper.insertSelective(noticeVo);
	}
	@Transactional(readOnly=true)
	@Override
	public Notice queryNoticeById(Integer id) {
		return this.noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateNotice(NoticeVo noticeVo) {
		this.noticeMapper.updateByPrimaryKeySelective(noticeVo);
	}

	@Override
	public void deleteNotice(Integer id) {
		this.noticeMapper.deleteByPrimaryKey(id);
	}
}
