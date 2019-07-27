package com.spring.bug.goods.service;

import com.spring.bug.goods.pojo.PurchaseRecordPo;

import java.util.List;

public interface PurchaseService {

    /**
     * 处理购买业务
     * @param userId 用户编号
     * @param productId 产品编号
     * @param quantity 购买数量
     * @return 成功or失败
     */
    public boolean purchase(Long userId, Long productId, int quantity);

    boolean purchaseRedis(Long userId, Long productId, int quantity);

    boolean dealRedisPurchase(List<PurchaseRecordPo> prpList);
}
