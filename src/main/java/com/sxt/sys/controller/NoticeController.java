package com.sxt.sys.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.sys.domain.Notice;
import com.sxt.sys.service.NoticeService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.NoticeVo;

@RestController
@RequestMapping("notice")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	
	/**
	 * 加载公告列表
	 */
	@RequestMapping("loadAllNotices")
	public DataGridView loadAllNotices(NoticeVo noticeVo) {
		return this.noticeService.queryAllNotices(noticeVo);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("addNotice")
	public ResultObj addNotice(NoticeVo noticeVo){
		try {
			//做添加
			noticeVo.setCreatetime(new Date());
			noticeVo.setOpername(WebUtils.getCurrentUserName());
			this.noticeService.addNotice(noticeVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("updateNotice")
	public ResultObj updateNotice(NoticeVo noticeVo){
		try {
			//做修改
			this.noticeService.updateNotice(noticeVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("deleteNotice")
	public ResultObj deleteNotice(NoticeVo noticeVo){
		try {
			//做删除
			this.noticeService.deleteNotice(noticeVo.getId());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping("batchDeleteNotice")
	public ResultObj batchDeleteNotice(NoticeVo noticeVo){
		try {
			//做删除
			Integer[] ids=noticeVo.getIds();
			if(null!=ids&&ids.length>0) {
				for (Integer integer : ids) {
					this.noticeService.deleteNotice(integer);
				}
			}
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 查看公告
	 */
	@RequestMapping("loadNoticeById")
	public Notice showNotice(NoticeVo noticeVo) {
		 Notice notice = this.noticeService.queryNoticeById(noticeVo.getId());
		return notice;
	}
}
