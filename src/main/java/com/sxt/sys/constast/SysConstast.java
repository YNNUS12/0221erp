package com.sxt.sys.constast;

public interface SysConstast {

	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER = 0;//超级管理员
	public static final Integer USER_TYPE_NORMAL = 1;//系统用户
	
	/**
	 * 可有状态
	 */
	public static final Integer AVAILABLE_TRUE = 1;
	public static final Integer AVAILABLE_FALSE = 0;
	
	/**
	 * 权限类型
	 */
	public static final String PERMISSION_TYPE_MENU = "menu";
	public static final String PERMISSION_TYPE_PERMISSION = "permission";
	
	
	/**
	 * 打开状态
	 */
	public static final Integer OPEN_TRUE=1;
	public static final Integer OPEN_FALSE=0;
	public static final String PWD_DEFALUT ="123456";
	
	
	//默认商品图片
	public static final Object GOODS_DEFAULT_IMAGE = "defaultgoodsimg.png";
	
	/**
	 * 用户操作常量
	 */
	Integer CODE_SUCCESS=1; //成功
	Integer CODE_ERROR=-1;//失败
	
	String ADD_SUCCESS="添加成功";
	String ADD_ERROR="添加失败";
	
	String UPDATE_SUCCESS="修改成功";
	String UPDATE_ERROR="修改失败";
	
	String DELETE_SUCCESS="删除成功";
	String DELETE_ERROR="删除失败";
	
	String DISPATCH_SUCCESS="分配成功";
	String DISPATCH_ERROR="分配失败";
	
	String RESET_SUCCESS="重置成功";
	String RESET_ERROR="重置失败";
	
	

}
