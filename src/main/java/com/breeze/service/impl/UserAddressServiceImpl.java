package com.breeze.service.impl;

import com.breeze.mapper.UserAddressMapper;
import com.breeze.pojo.UserAddress;
import com.breeze.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tx
 */
@Service
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findAll() {
        return userAddressMapper.findAll();
    }

    /**
     * 根据用户id查
     * @param userId 用户Id
     * @return
     */
    @Override
    public List<UserAddress> findByUserId(Long userId) {
        return userAddressMapper.findByUserId(userId);
    }

    /**
     * 添加用户地址
     * @param userAddress 用户地址
     * @return
     */
    @Override
    public int add(UserAddress userAddress) {
        if (userAddress == null || userAddress.getIsDefault() == null) {
            return 0;
        }

        if (userAddress.getIsDefault() == true) {
            userAddressMapper.updateIsDefault(userAddress.getUserId());
            return userAddressMapper.add(userAddress);
        } else {
            return userAddressMapper.add(userAddress);
        }
    }

    /**
     * 根据id删除
     * @param id 地址ID
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return userAddressMapper.deleteById(id);
    }

    /**
     * 根据用户ID修改地址信息
     * @param userAddress 用户地址
     * @return
     */
    @Override
    public int update(UserAddress userAddress) {

        if (userAddress == null || userAddress.getId() == null ||userAddress.getIsDefault() == null ){
            return 0;
        }

        if (userAddress.getIsDefault() == true){
            userAddressMapper.updateIsDefault(userAddress.getUserId());
            return userAddressMapper.update(userAddress);
        }else {
            return userAddressMapper.update(userAddress);
        }
    }

    /**
     * 根据用户Id查询默认地址
     * @param userId 用户Id
     * @return
     */
    @Override
    public UserAddress findDefaultAddressByUserId(Long userId) {
        return userAddressMapper.findDefaultAddressByUserId(userId);
    }

    /**
     * 根据地址Id查询地址
     *
     * @param id 地址Id
     * @return
     */
    @Override
    public UserAddress findById(Long id) {
        return userAddressMapper.findById(id);
    }
}
