package com.breeze.service.impl;

import com.alibaba.fastjson.JSON;
import com.breeze.mapper.ProductImageMapper;
import com.breeze.mapper.ProductMapper;
import com.breeze.mapper.UserMapper;
import com.breeze.pojo.bo.UserBuyHistoryBO;
import com.breeze.pojo.bo.ProductBidBO;
import com.breeze.pojo.bo.ProductUserBidBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.pojo.ProductBid;
import com.breeze.mapper.ProductBidMapper;
import com.breeze.service.IProductBidService;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 */
@Service
public class ProductBidServiceImpl implements IProductBidService {

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;
    @Resource
    private ProductBidMapper productBidMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductImageMapper imageMapper;

    @Override
    public int deleteById(Long id) {
        return productBidMapper.deleteById(id);
    }

    @Override
    public int add(ProductBid record) {
        return productBidMapper.add(record);
    }

    @Override
    public ProductBid findById(Long id) {
        return productBidMapper.findById(id);
    }

    @Override
    public int update(ProductBid record) {
        return productBidMapper.update(record);
    }

    @Override
    public int updateStatusByUserIdAndProductId(Integer status, Long userId, Long productId) {
        return productBidMapper.updateStatusByUserIdAndProductId(status, userId, productId);
    }

    @Override
    public ProductBid findHighBidByProductId(Long productId) {
        return productBidMapper.findHighBidByProductId(productId);
    }

    /**
     * 正在出价买的商品
     *
     * @return
     */
    @Override
    public List<ProductBidBO> findAll() {
        //所有正在出价的商品
        List<ProductBid> all = productBidMapper.findAll();
        //newList
        List<ProductBidBO> bidBO = new ArrayList<>();
        //遍历出价买的商品组装返回
        all.forEach(a -> {
            ProductBidBO productBidBO = new ProductBidBO();
            productBidBO.setId(a.getId());
            productBidBO.setProductId(a.getProductId());
            productBidBO.setMasterPicture(serverUrlPrefix + imageMapper.findMasterPictureByProductId(a.getProductId()));
            productBidBO.setProductName(productMapper.findById(a.getProductId()).getName());
            productBidBO.setUserName(userMapper.findById(a.getUserId()).getName());
            productBidBO.setPrice(a.getPrice());
            productBidBO.setCreateTime(a.getCreateTime());
            productBidBO.setFee(JSON.parse(a.getFee()));
            //添加返回
            bidBO.add(productBidBO);
        });
        return bidBO;
    }

    /**
     * 查询用户出价商品
     *
     * @return
     */
    @Override
    public List<ProductUserBidBO> findByUserId(Long userId) {
        //所有用户出价买的商品
        List<ProductBidBO> userSell = productBidMapper.findByUserId(userId);
        //newList
        List<ProductUserBidBO> bidBo = new ArrayList<>();
        //遍历出价买的商品组装返回
        userSell.forEach(BuyBO -> {
            /**
             * 下面都是组装
             */
            ProductUserBidBO productUserBidBO = new ProductUserBidBO();
            productUserBidBO.setMasterPicture(serverUrlPrefix + imageMapper.findMasterPictureByProductId(BuyBO.getProductId()));
            productUserBidBO.setId(BuyBO.getId());
            productUserBidBO.setProductName(productMapper.findById(BuyBO.getProductId()).getName());
            productUserBidBO.setUserName(BuyBO.getUserName());
            productUserBidBO.setPrice(BuyBO.getPrice());
            productUserBidBO.setCreateTime(BuyBO.getCreateTime());
            productUserBidBO.setFee(JSON.parse(BuyBO.getFee().toString()));
            //添加进新的BO返回
            bidBo.add(productUserBidBO);
        });
        return bidBo;
    }

    /**
     * 购买页历史
     *
     * @return
     */
    @Override
    public List<UserBuyHistoryBO> findUserBuyHistory(Long userId) {
        //查出购买的历史
        List<UserBuyHistoryBO> resultBuyHistory = productBidMapper.findUserBuyHistory(userId);
        //newList
        List<UserBuyHistoryBO> buyHistoryBOList = new ArrayList<>();
        //遍历购买历史
        resultBuyHistory.forEach(HistoryBO -> {
            /**
             * 组装成BO 返回
             */
            UserBuyHistoryBO userBuyHistoryBO = new UserBuyHistoryBO();
            userBuyHistoryBO.setOrderId(HistoryBO.getOrderId());
            userBuyHistoryBO.setProductId(HistoryBO.getProductId());
            userBuyHistoryBO.setMasterPicture(serverUrlPrefix + imageMapper.findMasterPictureByProductId(HistoryBO.getProductId()));
            userBuyHistoryBO.setProductName(productMapper.findById(HistoryBO.getProductId()).getName());
            userBuyHistoryBO.setPrice(HistoryBO.getPrice());
            userBuyHistoryBO.setCreateTime(HistoryBO.getCreateTime());
            //添加进新的List
            buyHistoryBOList.add(userBuyHistoryBO);
        });
        return buyHistoryBOList;
    }

    @Override
    public Integer findByUserIdAndPriceAndStatus(Long userId, BigDecimal price, Integer status) {
        return productBidMapper.findByUserIdAndPriceAndStatus(userId, price, status);
    }

    @Override
    public ProductBid findByProductIdAndUserIdAndStatus(Long productId, Long userId, Integer status) {
        return productBidMapper.findByProductIdAndUserIdAndStatus(productId, userId, status);
    }

    @Override
    public List<ProductBid> findByProductIdAndStatus(Long productId, Integer status) {
        return productBidMapper.findByProductIdAndStatus(productId, status);
    }

    /**
     * 用户出价买单数
     *
     * @param userId
     * @return
     */
    @Override
    public Long findBuyQuantity(Long userId) {
        return productBidMapper.findBuyQuantity(userId);
    }

    @Override
    public List<Long> findByUserIdAndStatusAndProductId(Long userId, Integer status, Long productId) {
        return productBidMapper.findByUserIdAndStatusAndProductId(userId, status, productId);
    }
}
