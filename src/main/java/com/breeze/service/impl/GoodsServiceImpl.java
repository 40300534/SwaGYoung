package com.breeze.service.impl;

import com.breeze.dao.GoodsDao;
import com.breeze.mapper.GoodsMapper;
import com.breeze.pojo.Cart;
import com.breeze.pojo.Goods;
import com.breeze.service.CartService;
import com.breeze.service.GoodsService;
import com.breeze.util.DateUtils;
import com.breeze.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsServiceImpl  implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods findById(String id) {
        if(!StringUtils.isEmpty(id)){
           return goodsMapper.findById(id);
        }
        return null;
    }

    @Override
    public PageInfo<Goods> search(Goods goods) {
        PageHelper.startPage(goods.getPageNum(),goods.getPageSize());
        List<Goods> goodsList =goodsMapper.search(goods);
        return new PageInfo<>(goodsList);
    }


    @Override
    public void save(Goods goods){
        goods.setId(UUIDUtils.getUUID());
        goods.setCreate_time(DateUtils.getDay());
        goodsDao.save(goods);
    }

    @Override
    public void update(Goods goods){
        goodsDao.save(goods);
    }




    @Override
    public void addCart(Goods goods) {

        Cart cart = new Cart();
        cart.setId(UUIDUtils.getUUID());
        cart.setPrice(goods.getGoods_price());
        cart.setRelevance_name(goods.getGoods_name());
        cart.setRelevance_id(goods.getId());
        cart.setUser_id("当前登录人");
        cartService.addCart(cart);

    }


}
