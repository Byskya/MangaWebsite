package com.pratice.shopcar.service.impl;

import com.pratice.shopcar.mappers.CollectMapper;
import com.pratice.shopcar.pojo.Collection;
import com.pratice.shopcar.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    @Override
    public void collectGoods(String username, Collection collection) {
        collection.setCreatedTime(new Date());
        collection.setCreatedUser(username);
        collection.setModifiedTime(new Date());
        collection.setModifiedUser(username);
        insert(collection);
    }

    private void insert(Collection collection) {
        collectMapper.insert(collection);
    }

    @Override
    public List<Collection> getAllCollectionsByUid(Integer uid) {
        List<Collection> list = collectMapper.findAllCollectionsByUid(uid);

        return list;
    }

    @Override
    public void deleteCollectoin(long id) {
        collectMapper.deleteCollection(id);
        return;
    }
}
