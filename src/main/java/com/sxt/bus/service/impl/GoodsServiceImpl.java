package com.sxt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Goods;
import com.sxt.bus.mapper.GoodsMapper;
import com.sxt.bus.service.GoodsService;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.sys.utils.DataGridView;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;

	@Transactional(readOnly=true)
	@Override
	public DataGridView queryAllGoods(GoodsVo goodsVo) {
		Page<Object> page=PageHelper.startPage(goodsVo.getPage(), goodsVo.getLimit());
		List<Goods> data = this.goodsMapper.queryAllGoods(goodsVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addGoods(GoodsVo goodsVo) {
		this.goodsMapper.insertSelective(goodsVo);
	}

	@Override
	public void updateGoods(GoodsVo goodsVo) {
		this.goodsMapper.updateByPrimaryKeySelective(goodsVo);
	}

	@Override
	public void deleteGoods(Integer id) {
		this.goodsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Goods queryGoodsById(Integer id) {
		return this.goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Goods> queryGoodsByProviderId(Integer providerid) {
		Goods goods=new Goods();
		goods.setProviderid(providerid);
		return this.goodsMapper.queryAllGoods(goods);
	}
	
	
	
	

}
