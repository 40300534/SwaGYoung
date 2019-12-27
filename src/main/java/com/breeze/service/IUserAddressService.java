package com.breeze.service;

import com.breeze.pojo.UserAddress;

import java.util.List;


/**
 * @author 莫奇
 */
public interface IUserAddressService {
    /**
     * 查询所有
     * @return
     */
    List<UserAddress> findAll();

    /**
     * 根据用户id查询
     * @param userId 用户Id
     * @return
     */
    List<UserAddress> findByUserId(Long userId);

    /**
     * 添加地址
     * @param userAddress 用户地址
     * @return
     */
    int add(UserAddress userAddress);

    /**
     * 根据id删除
     * @param id 地址ID
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据信息修改
     * @param userAddress 用户地址
     * @return
     */
    int update(UserAddress userAddress);

    /**
     * 通过用户id查询默认地址
     * @param userId 用户Id
     * @return
     */
    UserAddress findDefaultAddressByUserId(Long userId);

    /**
     * 根据Id查询地址
     * @param id 地址Id
     * @return
     */
    UserAddress findById(Long id);
}
