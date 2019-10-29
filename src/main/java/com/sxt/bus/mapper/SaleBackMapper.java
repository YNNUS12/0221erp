package com.sxt.bus.mapper;

import com.sxt.bus.domain.SaleBack;

public interface SaleBackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SaleBack record);

    int insertSelective(SaleBack record);

    SaleBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SaleBack record);

    int updateByPrimaryKey(SaleBack record);
}