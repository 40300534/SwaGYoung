package com.breeze.service.impl;

import com.alibaba.fastjson.JSON;
import com.breeze.mapper.ProductImageMapper;
import com.breeze.mapper.ProductMapper;
import com.breeze.mapper.UserMapper;
import com.breeze.pojo.bo.UserSellHistoryBO;
import com.breeze.pojo.bo.ProductAskBO;
import com.breeze.pojo.bo.ProductUserAskBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.mapper.ProductAskMapper;
import com.breeze.pojo.ProductAsk;
import com.breeze.service.IProductAskService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
@Service
public class ProductAskServiceImpl implements IProductAskService {

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Resource
    private ProductAskMapper productAskMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductImageMapper imageMapper;
    @Override
    public int deleteById(Long id) {
        return productAskMapper.deleteById(id);
    }

    @Override
    public int add(ProductAsk record) {
        return productAskMapper.add(record);
    }

    @Override
    public ProductAsk findById(Long id) {
        return productAskMapper.findById(id);
    }

    @Override
    public int update(ProductAsk record) {
        return productAskMapper.update(record);
    }

    @Override
    public BigDecimal findHighestAsk(Long productId) {
        return productAskMapper.findHighestAsk(productId);
    }

    @Override
    public BigDecimal findLowestAsk(Long productId) {
        return productAskMapper.findLowestAsk(productId);
    }
    /**
     * 正在出价卖的商品
     */
    @Override
    public List<ProductAskBO> findAll() {
        //查所有订单
        List<ProductAsk> productAsk = productAskMapper.findAll();
        //newBO
        List<ProductAskBO> productAskBO = new ArrayList<>();
        //遍历order
        productAsk.forEach(a -> {
            ProductAskBO askBO = new ProductAskBO();
            //ID
            askBO.setId(a.getId());
            //商品ID
            askBO.setProductId(a.getProductId());
            //图片
            askBO.setMasterPicture(serverUrlPrefix+imageMapper.findMasterPictureByProductId(a.getProductId()));
            //商品名
            askBO.setProductName(productMapper.findById(a.getProductId()).getName());
            //用户名字
            askBO.setUserName(userMapper.findById(a.getUserId()).getName());
            //订单成交价
            askBO.setPrice(a.getPrice());
            //订单创建时间
            askBO.setCreateTime(a.getCreateTime());
            //库存
            askBO.setInventory(a.getInventory());
            //费用
            askBO.setFee(JSON.parse(a.getFee()));
            productAskBO.add(askBO);
        });
        return productAskBO;
    }

    /**
     * 获取用户出售商品
     */
    @Override
    public List<ProductUserAskBO> findByUserId(Long userId) {
        //查出所有
        List<ProductAskBO> userSells = productAskMapper.findByUserId(userId);
        //newBO
        List<ProductUserAskBO> productUserAskBOList = new ArrayList<>();

        userSells.forEach(BuyBO -> {
            ProductUserAskBO productUserAskBO = new ProductUserAskBO();
            //ID
            productUserAskBO.setId(BuyBO.getId());
            //图片
            productUserAskBO.setMasterPicture(serverUrlPrefix+imageMapper.findMasterPictureByProductId(BuyBO.getProductId()));
            //商品名
            productUserAskBO.setProductName(productMapper.findById(BuyBO.getProductId()).getName());
            //用户名
            productUserAskBO.setUserName(BuyBO.getUserName());
            //价格
            productUserAskBO.setPrice(BuyBO.getPrice());
            //创建时间
            productUserAskBO.setCreateTime(BuyBO.getCreateTime());
            //库存
            productUserAskBO.setInventory(BuyBO.getInventory());
            //费用
            productUserAskBO.setFee(JSON.parse(BuyBO.getFee().toString()));
            //组装好返回
            productUserAskBOList.add(productUserAskBO);
        });
        return productUserAskBOList;
    }

    @Override
    public ProductAsk findByUserIdAndProductId(Long userId, Long productId) {
        return productAskMapper.findByUserIdAndProductId(userId,productId);
    }

    /**
     * 出售历史
     * @return
     */
    @Override
    public List<UserSellHistoryBO> findUserSellHistory(Long userId) {
        //用户出价成交
        List<UserSellHistoryBO> resultSellHistories = productAskMapper.findUserSellHistory(userId);
        //newList
        List<UserSellHistoryBO> sellHistoryList = new ArrayList<>();
        //遍历出价历史组装返回
        resultSellHistories.forEach(sellHistory -> {
            UserSellHistoryBO userSellHistoryBO = new UserSellHistoryBO();
            userSellHistoryBO.setOrderId(sellHistory.getOrderId());
            userSellHistoryBO.setProductId(sellHistory.getProductId());
            //拼接图片地址
            userSellHistoryBO.setImage(serverUrlPrefix+imageMapper.findMasterPictureByProductId(sellHistory.getProductId()));
            //商品名
            userSellHistoryBO.setProductName(productMapper.findById(sellHistory.getProductId()).getName());
            userSellHistoryBO.setPrice(sellHistory.getPrice());
            userSellHistoryBO.setCreateTime(sellHistory.getCreateTime());
            sellHistoryList.add(userSellHistoryBO);
        });
        return sellHistoryList;
    }

    @Override
    public int decreaseStock(Long id) {
        return productAskMapper.decreaseStock(id);
    }

    /**
     * 用户出价卖单数
     * @param userId
     * @return
     */
    @Override
    public Long sellQuantity(Long userId) {
        return productAskMapper.sellQuantity(userId);
    }
}
