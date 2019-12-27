package com.breeze.mapper;

import com.breeze.pojo.UserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莫奇
 */
public interface UserAddressMapper {
    /**
     * 查所有
     *
     * @return
     */
    List<UserAddress> findAll();

    /**
     * 根据用户id查
     *
     * @param userId
     * @return
     */
    List<UserAddress> findByUserId(Long userId);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 修改
     *
     * @param userAddress
     * @return
     */
    int update(UserAddress userAddress);

    /**
     * 添加收货地址
     *
     * @param userAddress
     * @retur
     */
    int add(UserAddress userAddress);

    /**
     * 通过用户id查询默认地址
     * @return UserAddress
     */
    UserAddress findDefaultAddressByUserId(Long userId);

    /**
     * 根据地址ID查询地址
     * @param id
     * @return
     */
    UserAddress findById(Long id);

    /**
     * 修改默认地址
     */
    void updateIsDefault(Long userId);
}
