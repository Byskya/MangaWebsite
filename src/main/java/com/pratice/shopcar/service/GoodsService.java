package com.pratice.shopcar.service;

import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.pojo.Goods;

import java.math.BigInteger;
import java.util.List;

public interface GoodsService {
    PageInfo<Goods> getHotGoods(Integer pageNum);

    Goods getById(Long id);

    List<Goods> getAllGoodsById(long goodsId);

    PageInfo<Goods> getGoodsLikeName(String goodsName,Integer pageNum);

    Goods getByUserName(String goodsName);

    PageInfo<Goods> getGoodsCategory(BigInteger categoryId,Integer pageNum);
}
