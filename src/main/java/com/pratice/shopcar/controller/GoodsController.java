package com.pratice.shopcar.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.pratice.shopcar.pojo.Goods;
import com.pratice.shopcar.service.GoodsService;
import com.pratice.shopcar.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/hot/{pageNum}")
    public ResponseResult<PageInfo<Goods>> getHotGoods(@PathVariable("pageNum") Integer pageNum, Model model) {
        // 获取数据
        PageInfo<Goods> data = goodsService.getHotGoods(pageNum);
        System.out.println(data);
        System.out.println("*************************************分页数据："+data);
        // 返回
        ResponseResult<PageInfo<Goods>> rr = new ResponseResult<PageInfo<Goods>>();
        model.addAttribute("prePage23",data.getPrePage());
        model.addAttribute("nextPage23",data.getNextPage());
        rr.setState(200);
        rr.setData(data);
        // 返回成功
        return rr;
    }
    @GetMapping("/{id}/details")
    public ResponseResult<Goods> getById(@PathVariable("id") Long id) {
        // 执行
        Goods data = goodsService.getById(id);
        System.out.println("*********************************"+data.getImage());
        ResponseResult<Goods> rr = new ResponseResult<Goods>();
        rr.setData(data);
        rr.setState(200);
        // 返回
        return rr;
    }
    @RequestMapping("/read/{id}")
    public ResponseResult<Goods> getMangaById(@PathVariable("id") long goodsId,HttpSession session){
        System.out.println("接收到阅读请求********************"+goodsId);
        Goods goods = goodsService.getById(goodsId);
        ResponseResult<Goods> rr= new ResponseResult<Goods>();
        rr.setData(goods);
        rr.setState(200);
        return rr;
    }
    @RequestMapping("/search/{goodsName}/{pageNum}")
    public ResponseResult<PageInfo<Goods>> getMangaLikeName(@PathVariable("goodsName") String goodsName,@PathVariable("pageNum") Integer pageNum ,Model model){
        System.out.println("模糊查询关键词："+goodsName);
        PageInfo<Goods> data = goodsService.getGoodsLikeName(goodsName,pageNum);
        System.out.println("*****************************收藏的分页数据："+data);
        // 返回
        ResponseResult<PageInfo<Goods>> rr = new ResponseResult<PageInfo<Goods>>();
        model.addAttribute("prePage23",data.getPrePage());
        model.addAttribute("nextPage23",data.getNextPage());
        rr.setState(200);
        rr.setData(data);
        // 返回成功
        return rr;
    }
    @RequestMapping("/category/{categoryId}/{pageNum}")
    public ResponseResult<PageInfo<Goods>> getMangaCategory(@PathVariable("categoryId")BigInteger categoryId,@PathVariable("pageNum") Integer pageNum){
        System.out.println("**********************分类id:"+categoryId);
        ResponseResult<PageInfo<Goods>> rr = new ResponseResult<PageInfo<Goods>>();
        PageInfo<Goods> data = goodsService.getGoodsCategory(categoryId,pageNum);
        System.out.println("************************作品分类："+data);
        rr.setData(data);
        rr.setState(200);
        return rr;
    }

}
