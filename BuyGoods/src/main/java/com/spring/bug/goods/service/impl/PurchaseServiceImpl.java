package com.spring.bug.goods.service.impl;

import com.spring.bug.goods.dao.ProductDao;
import com.spring.bug.goods.dao.PurchaseReocrdDao;
import com.spring.bug.goods.pojo.ProductPo;
import com.spring.bug.goods.pojo.PurchaseRecordPo;
import com.spring.bug.goods.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private ProductDao productDao = null;

    @Autowired
    private PurchaseReocrdDao purchaseReocrdDao = null;

    @Override
    //启动 Spring 数据库事务机制
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {
        ProductPo productPo = productDao.getProduct(productId);

        //比较库存和购买数量
        if (productPo.getStock() < quantity) {
            return false;
        }

        productDao.decreaseProduct(productId, quantity);
        PurchaseRecordPo purchaseRecordPo = this.initPurchaseRecord(userId, productPo, quantity);
        return false;
    }

    //初始化购买数量
    private PurchaseRecordPo initPurchaseRecord(Long userId, ProductPo product, int quantity) {
        PurchaseRecordPo purchaseRecordPo = new PurchaseRecordPo();
        purchaseRecordPo.setNote("购买日志，时间：" + System.currentTimeMillis());
        purchaseRecordPo.setPrice(product.getPrice());
        purchaseRecordPo.setProductId(product.getId());
        purchaseRecordPo.setQuantity(quantity);
        double sum = product.getPrice() * quantity;
        purchaseRecordPo.setSum(sum);
        purchaseRecordPo.setUserId(userId);
        return purchaseRecordPo;
    }
}
