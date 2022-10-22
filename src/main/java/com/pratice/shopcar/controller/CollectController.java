package com.pratice.shopcar.controller;

import com.pratice.shopcar.pojo.Collection;
import com.pratice.shopcar.pojo.Goods;
import com.pratice.shopcar.service.CollectService;
import com.pratice.shopcar.service.GoodsService;
import com.pratice.shopcar.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class CollectController {
    @Autowired
    CollectService collectService;
    @Autowired
    GoodsService goodsService;
    @PostMapping("/collect")
    public ResponseResult<Void> collect(Collection collection, HttpSession session){
        Integer uid =Integer.valueOf(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        collection.setUid(uid);
        System.out.println("收藏商品的id*********************"+collection.getGoodsId());
        collectService.collectGoods(username,collection);
        ResponseResult<Void> rr = new ResponseResult<Void>();
        rr.setState(200);
        return rr;
    }
    @RequestMapping("/")
    public ResponseResult<List<Goods>> getAllCollections(HttpSession session){
        ResponseResult<List<Goods>> rr = new ResponseResult<List<Goods>>();
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        List<Collection> collections = collectService.getAllCollectionsByUid(uid);
        System.out.println("收藏品数量："+collections.size());
        long goodsId;
        List<Goods> listGoods=new ArrayList<Goods>();
        for (int i =0;i<collections.size();i++){
            goodsId = collections.get(i).getGoodsId();
            System.out.println(goodsId);
            Goods goods = goodsService.getById(goodsId);
            goods.setId(goodsId);
            listGoods.add(goods);
        }
        System.out.println("用户的所有收藏品：*****************************"+listGoods);
        rr.setData(listGoods);
        rr.setState(200);
        return rr;
    }

    @RequestMapping("/collection/delete/{goodsId}")
    public ResponseResult<Void> deleteCollection(@PathVariable("goodsId") long goodsId){
        System.out.println("test删除收藏*************************");
        ResponseResult<Void> rr = new ResponseResult<Void>();
        collectService.deleteCollectoin(goodsId);
        rr.setState(200);
        return rr;
    }
}
