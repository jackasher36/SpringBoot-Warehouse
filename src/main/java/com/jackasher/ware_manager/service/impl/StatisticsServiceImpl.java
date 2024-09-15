package com.jackasher.ware_manager.service.impl;

import com.jackasher.ware_manager.entity.Statistics;
import com.jackasher.ware_manager.mapper.StatisticsMapper;
import com.jackasher.ware_manager.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    //注入StatisticsMapper
    @Autowired
    private StatisticsMapper statisticsMapper;

    //统计各个仓库商品库存数量的业务方法
    @Override
    public List<Statistics> statisticsStoreInvent() {
        return statisticsMapper.statisticsStoreInvent();
    }
}
