package com.breeze.service.impl;

import com.breeze.enums.FeeTypeEnum;
import com.breeze.enums.TransactionTypeEnum;
import com.breeze.mapper.*;
import com.breeze.pojo.bo.FeeBO;
import com.breeze.pojo.bo.ProductBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.pojo.Fee;
import com.breeze.service.IFeeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 14:11
 */
@Service
public class FeeServiceImpl implements IFeeService {

    @Resource
    private FeeMapper feeMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductAskMapper productUserSellMapper;

    @Resource
    private DepositMapper depositMapper;

    @Override
    public int deleteById(Integer id) {
        return feeMapper.deleteById(id);
    }

    @Override
    public int add(Fee record) {
        return feeMapper.add(record);
    }

    @Override
    public Fee findById(Integer id) {
        return feeMapper.findById(id);
    }

    @Override
    public int update(Fee record) {
        return feeMapper.update(record);
    }

    @Override
    public BigDecimal countAmount(Integer userTypeId, Long productId, BigDecimal price, Integer transactionTypeId, Integer isSeller) {
        //判断价格
        if (price == null) {
            return new BigDecimal("0");
        }
        int priceResult = price.compareTo(BigDecimal.ZERO);

        if (priceResult == 0 || priceResult < 0) {
            return new BigDecimal("0");
        }
        if (userTypeId == null || transactionTypeId == null || productId == null) {
            return null;
        }
        List<FeeBO> fees = feeMapper.findFeeByUserTypeIdAndIsSeller(userTypeId, isSeller);
        BigDecimal totalAmount = new BigDecimal(0);
        //立刻买
        if (TransactionTypeEnum.NOW_BUY.getCode().equals(transactionTypeId)) {
            ProductBO productInfo = productMapper.findProductBuyBaseInfoByProductId(productId);
            //当前产品价格（计算费用）
            BigDecimal productPrice = productInfo.getPrice();
            for (FeeBO feeBO : fees) {
                if (FeeTypeEnum.FIXED.getCode().equals(feeBO.getFeeTypeId())) {
                    //获取固定值，相加
                    totalAmount = totalAmount.add(new BigDecimal(feeBO.getValue()));
                }
            }
            totalAmount = totalAmount.add(productPrice);
            return totalAmount;
        }
        //立刻卖
        if (TransactionTypeEnum.NOW_SELL.getCode().equals(transactionTypeId)) {
            //获取当前最高价格
            BigDecimal highestBid = productUserSellMapper.findHighestAsk(productId);
            //判断是否有价格变动
            //TODO 需要验证一下
            if (price.compareTo(highestBid) != 0) {
                return null;
            }
            for (FeeBO feeBO : fees) {
                if (FeeTypeEnum.FIXED.getCode().equals(feeBO.getFeeTypeId())) {
                    //获取固定值，相加
                    totalAmount = totalAmount.add(new BigDecimal(feeBO.getValue()));
                } else if (FeeTypeEnum.PERCENT.getCode().equals(feeBO.getFeeTypeId())) {
                    //获取百分比
                    BigDecimal decimal1 = new BigDecimal(feeBO.getValue());
                    //当前总价格+(总价格*（decimal1/100）)
                    BigDecimal decimal2 = highestBid.multiply(decimal1.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
                    totalAmount = totalAmount.add(decimal2);
                }
            }
            //预计收入 = 商品总价 - 所需费用
            totalAmount = highestBid.subtract(totalAmount);
            return totalAmount;
        }
        return null;
    }

    @Override
    public List<FeeBO> findFeeByUserTypeId(Integer userTypeId) {
        return feeMapper.findFeeByUserTypeId(userTypeId);
    }

    @Override
    public List<FeeBO> findFeeByUserTypeIdAndIsSeller(Integer userTypeId, Integer isSeller) {
        return feeMapper.findFeeByUserTypeIdAndIsSeller(userTypeId, isSeller);
    }
}
