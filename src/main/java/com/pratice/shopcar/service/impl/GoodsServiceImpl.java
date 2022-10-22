package com.pratice.shopcar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.mappers.GoodsMapper;
import com.pratice.shopcar.pojo.Goods;
import com.pratice.shopcar.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    public PageInfo<Goods> getHotGoods(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        List<Goods> list = findHotGoods();
        PageInfo<Goods> page = new PageInfo<Goods>(list,5);
        return page;
    }
    private List<Goods> findHotGoods() {
        return goodsMapper.findHotGoods();
    }

    @Override
    public Goods getById(Long id) {
        return findById(id);
    }
    private Goods findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public List<Goods> getAllGoodsById(long goodsId) {

        return goodsMapper.findAllById(goodsId);
    }

    @Override
    public PageInfo<Goods> getGoodsLikeName(String goodsName,Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        List<Goods> list = goodsMapper.findLikeName(goodsName);
        PageInfo<Goods> page = new PageInfo<Goods>(list,5);
        return page;
    }

    @Override
    public Goods getByUserName(String goodsName) {
        Goods goods = goodsMapper.findByGoodsName(goodsName);
        return goods;
    }

    @Override
    public PageInfo<Goods> getGoodsCategory(BigInteger categoryId,Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        List<Goods> list = goodsMapper.findGoodsCategory(categoryId);
        PageInfo<Goods> page = new PageInfo<Goods>(list,5);
        return page;
    }
}
