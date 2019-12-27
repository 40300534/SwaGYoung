package com.breeze.mapper;

import com.breeze.pojo.UserPaymentAccount;
import com.breeze.pojo.bo.UserPaymentAccountBO;

import java.util.List;

/**
 * @author 莫奇
 */
public interface UserPaymentAccountMapper {

    /**
     * 根据用户Id删除指定账户
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 新增账户
     * @param record
     * @return
     */
    int add(UserPaymentAccount record);


    /**
     * 根据账户Id查询账户信息
     * @param id
     * @return
     */
    UserPaymentAccount findById(Long id);

    /**
     * 修改账户信息
     * @param record
     * @return
     */
    int update(UserPaymentAccount record);

    /**
     * 查询全部用户的账户
     * @return
     */
    List<UserPaymentAccount> findAll();

    /**
     * 根据用户Id查询用户账户
     * @param userId
     * @return
     */
    List<UserPaymentAccountBO> findByUserId(Long userId);

    /**
     *根據用戶Id和賬戶類型Id刪除賬戶
     * @param userId
     * @param paymentTypeId
     */
    void deleteByUserIdAndPaymentTypeId(Long userId, Long paymentTypeId);

    /**
     * 根據用戶Id和賬戶類型Id查詢賬戶
     * @param userId
     * @param paymentTypeId
     */
    UserPaymentAccount findByUserIdAndUserPaymentId(Long userId, Long paymentTypeId);
}