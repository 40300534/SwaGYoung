package com.breeze.mapper;

import com.breeze.pojo.bo.FeeBO;
import org.apache.ibatis.annotations.Param;

import com.breeze.pojo.Fee;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 14:11
 * @Description: 一句话描述该类的功能
 */
public interface FeeMapper {

    int add(Fee record);

    Fee findById(@Param("id") Integer id);

    int update(@Param("updated")Fee updated);

    int deleteById(@Param("id") Integer id);

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