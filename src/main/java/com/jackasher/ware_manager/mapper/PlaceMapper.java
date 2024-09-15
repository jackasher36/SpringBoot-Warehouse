package com.jackasher.ware_manager.mapper;

import com.jackasher.ware_manager.entity.Place;

import java.util.List;

public interface PlaceMapper {

    //查询所有产地
    public List<Place> findAllPlace();
}
