package com.sxt.bus.service;

import java.util.List;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.sys.utils.DataGridView;

/**
 * 商品管理的服务接口
 * @author LJH
 *
 */
public interface GoodsService {
	/**
	 * 分页查询
	 */
	public DataGridView queryAllGoods(GoodsVo goodsVo);
	/**
	 * 添加商品
	 * @param goodsVo
	 */
	public void addGoods(GoodsVo goodsVo);
	/**
	 * 修改商品
	 * @param goodsVo
	 */
	public void updateGoods(GoodsVo goodsVo);
	/**
	 * 删除商品
	 * @param id
	 */
	public void deleteGoods(Integer id);
	
	/**
	 * 根据ID查询商品信息
	 * @param id
	 * @return
	 */
	public Goods queryGoodsById(Integer id);
	
	/**
	 * 根据供应商ID查询商品
	 * @param providerid
	 * @return
	 */
	public List<Goods> queryGoodsByProviderId(Integer providerid);
}
