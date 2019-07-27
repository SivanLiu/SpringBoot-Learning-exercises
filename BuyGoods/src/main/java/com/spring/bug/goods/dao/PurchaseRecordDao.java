package com.spring.bug.goods.dao;

import com.spring.bug.goods.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseRecordDao {
    public int insertPurchaseRecord(PurchaseRecordPo pr);
}
