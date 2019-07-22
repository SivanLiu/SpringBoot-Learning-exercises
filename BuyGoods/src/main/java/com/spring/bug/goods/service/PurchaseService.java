package com.spring.bug.goods.service;

public interface PurchaseService {

    /**
     * 处理购买业务
     * @param userId
     * @param productId
     * @param quantity
     * @return
     */
    public boolean purchase(Long userId, Long productId, int quantity);
}
