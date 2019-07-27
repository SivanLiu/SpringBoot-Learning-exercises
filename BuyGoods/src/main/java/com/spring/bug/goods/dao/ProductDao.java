package com.spring.bug.goods.dao;

import com.spring.bug.goods.pojo.ProductPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductDao {
    //获取产品
    public ProductPo getProduct(Long id);

    //减库存，而 @Param 表明 mybatis 参数传递给后台
//    public int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity, @Param("version") int version);

    //减库存，而@Param标明MyBatis参数传递给后台
    public int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity);
}
