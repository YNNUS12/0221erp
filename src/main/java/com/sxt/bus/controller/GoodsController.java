package com.sxt.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sxt.bus.domain.Goods;
import com.sxt.bus.service.GoodsService;
import com.sxt.bus.vo.GoodsVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.AppFileUtils;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;

/**
 * 商品管理控制器
 * @author LJH
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	
	
	/**
	 * 加载商品管理列表
	 */
	@RequestMapping("loadAllGoods")
	public DataGridView loadAllGoods(GoodsVo goodsVo) {
		return this.goodsService.queryAllGoods(goodsVo);
	}
	
	/**
	 * 添加
	 */
	@RequestMapping("addGoods")
	public ResultObj addGoods(GoodsVo goodsVo) {
		try {
			if(!goodsVo.getGoodsimg().equals(SysConstast.GOODS_DEFAULT_IMAGE)) {
				AppFileUtils.updateFileName(goodsVo.getGoodsimg());
			}
			goodsVo.setGoodsimg(goodsVo.getGoodsimg().replace("_temp", ""));
			this.goodsService.addGoods(goodsVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	/**
	 * 修改
	 */
	@RequestMapping("updateGoods")
	public ResultObj updateGoods(GoodsVo goodsVo) {
		try {
			/**
			 * 如果 不是默认图片就处理图片
			 * 		|--以temp结尾  要改文件名和保存的路径  并删除原来的文件
			 * 		|--不以temp结尾说明用户没有修改图片
			 * 如果是默认图片呢
			 * 	不做处理
			 */
			if(!goodsVo.getGoodsimg().equals(SysConstast.GOODS_DEFAULT_IMAGE)) {
				if(goodsVo.getGoodsimg().endsWith("_temp")) {
					AppFileUtils.updateFileName(goodsVo.getGoodsimg());
					goodsVo.setGoodsimg(goodsVo.getGoodsimg().replace("_temp", ""));
					//删除原来的图片
					Goods goods=this.goodsService.queryGoodsById(goodsVo.getId());
					AppFileUtils.deleteFileByName(goods.getGoodsimg());
				}
			}
			this.goodsService.updateGoods(goodsVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("deleteGoods")
	public ResultObj deleteGoods(GoodsVo goodsVo) {
		try {
			this.goodsService.deleteGoods(goodsVo.getId());
			AppFileUtils.deleteFileByName(goodsVo.getGoodsimg());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 根据供应商ID查询商品
	 */
	@RequestMapping("loadGoodsByProviderId")
	public List<Goods> loadGoodsByProviderId(Integer providerid){
		return this.goodsService.queryGoodsByProviderId(providerid);
	}
	
}
