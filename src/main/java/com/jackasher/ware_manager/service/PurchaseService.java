package com.jackasher.ware_manager.service;

import com.jackasher.ware_manager.entity.Purchase;
import com.jackasher.ware_manager.entity.Result;
import com.jackasher.ware_manager.page.Page;

public interface PurchaseService {

    //添加采购单的业务方法
    public Result savePurchase(Purchase purchase);

    //分页查询采购单的业务方法
    public Page queryPurchasePage(Page page, Purchase purchase);

    //修改采购单的业务方法
    public Result updatePurchase(Purchase purchase);

    //删除采购单的业务方法
    public Result deletePurchase(Integer buyId);
}
