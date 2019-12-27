package com.breeze.service;

import com.breeze.pojo.Fee;
import com.breeze.pojo.bo.FeeBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 14:11
 */
public interface IFeeService {


    int deleteById(Integer id);

    int add(Fee record);

    Fee findById(Integer id);

    int update(Fee record);

    /**
     * 计算费用
     * @param userTypeId 当前操作用户的用户类型Id
     * @param price 当前产品的买卖价格
     * @param transactionTypeId 交易类型Id
     * @param isSeller 是否是卖家
     * @return BigDecimal
     */
    BigDecimal countAmount(Integer userTypeId,Long productId, BigDecimal price,Integer transactionTypeId,Integer isSeller);

    /**
     * 通过当前登录用户的typeId查询费用
     * @param userTypeId
     * @return
     */
    List<FeeBO> findFeeByUserTypeId(Integer userTypeId);

    /**
     * 通过当前登录用户的typeId和是否是卖家查询费用
     * @param userTypeId
     * @return
     */
    List<FeeBO> findFeeByUserTypeIdAndIsSeller(Integer userTypeId,Integer isSeller);

}
