package com.jackasher.ware_manager.mapper;

import com.jackasher.ware_manager.entity.Unit;

import java.util.List;

public interface UnitMapper {

    //查询所有单位的方法
    public List<Unit> findAllUnit();
}
