package com.pratice.shopcar.mappers;

import com.pratice.shopcar.pojo.Collection;

import java.util.List;

public interface CollectMapper {
    void insert(Collection collection);

    List<Collection> findAllCollectionsByUid(Integer uid);

    void deleteCollection(long id);
}
