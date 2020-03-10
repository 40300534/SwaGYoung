package com.breeze.service;


import com.breeze.pojo.Goods;
import com.github.pagehelper.PageInfo;


public interface GoodsService {
    Goods findById(String id);

    PageInfo<Goods> search(Goods goods);

    void save(Goods goods);

    void update(Goods goods);

    void addCart(Goods goods);
}
