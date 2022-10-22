package com.pratice.shopcar.pojo;

public class Collection extends BasePojo{
    private static final long serialVersionUID = 2267947360887498964L;
    private Integer id;
    private Integer goodsId;
    private Integer uid;

    public Collection() {
    }

    public Collection(Integer id, Integer goodsId, Integer uid) {
        this.id = id;
        this.goodsId = goodsId;
        this.uid = uid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", uid=" + uid +
                '}';
    }
}
