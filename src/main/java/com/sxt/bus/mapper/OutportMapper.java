package com.sxt.bus.mapper;

import com.sxt.bus.domain.Outport;

public interface OutportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Outport record);

    int insertSelective(Outport record);

    Outport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Outport record);

    int updateByPrimaryKey(Outport record);
}