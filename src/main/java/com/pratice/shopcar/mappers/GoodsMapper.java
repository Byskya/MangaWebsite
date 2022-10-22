package com.pratice.shopcar.mappers;

import com.pratice.shopcar.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface GoodsMapper {
    List<Goods> findHotGoods();

    Goods findById(Long id);

    List<Goods> findAllById(long goodsId);

    List<Goods> findLikeName(@Param("goodsName") String goodsName);

    Goods findByGoodsName(String goodsName);

    List<Goods> findGoodsCategory(BigInteger categoryId);
}
