package com.sxt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Goods;
import com.sxt.bus.domain.Inport;
import com.sxt.bus.mapper.GoodsMapper;
import com.sxt.bus.mapper.InportMapper;
import com.sxt.bus.service.InportService;
import com.sxt.bus.vo.InportVo;
import com.sxt.sys.utils.DataGridView;

@Service
@Transactional
public class InportServiceImpl implements InportService{
	
	@Autowired
	private InportMapper inportMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Transactional(readOnly=true)
	@Override
	public DataGridView queryAllInport(InportVo inportVo) {
		Page<Object> page=PageHelper.startPage(inportVo.getPage(), inportVo.getLimit());
		List<Inport> data = this.inportMapper.queryAllInport(inportVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addInport(InportVo inportVo) {
		this.inportMapper.insertSelective(inportVo);
		
		//更新商品库存
		Goods goods = goodsMapper.selectByPrimaryKey(inportVo.getGoodsid());
		goods.setNumber(goods.getNumber()+inportVo.getNumber());
		this.goodsMapper.updateByPrimaryKeySelective(goods);
	}

	@Override
	public void updateInport(InportVo inportVo) {
		//查询未修改的原数据
		Inport inport = this.inportMapper.selectByPrimaryKey(inportVo.getId());
		//更新商品库存
		Goods goods = goodsMapper.selectByPrimaryKey(inportVo.getGoodsid());
		goods.setNumber(goods.getNumber()-inport.getNumber()+inportVo.getNumber());
		this.goodsMapper.updateByPrimaryKeySelective(goods);
		
		this.inportMapper.updateByPrimaryKeySelective(inportVo);
	}

	@Override
	public void deleteInport(InportVo inportVo) {
		this.inportMapper.deleteByPrimaryKey(inportVo.getId());
		
		Goods goods = goodsMapper.selectByPrimaryKey(inportVo.getGoodsid());
		goods.setNumber(goods.getNumber()-inportVo.getNumber());
		this.goodsMapper.updateByPrimaryKeySelective(goods);
	}
}
