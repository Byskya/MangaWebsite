package com.pratice.shopcar.service;

import com.pratice.shopcar.pojo.Collection;

import java.util.List;

public interface CollectService {
    void collectGoods(String username, Collection collection);

    List<Collection> getAllCollectionsByUid(Integer uid);

    void deleteCollectoin(long id);
}
