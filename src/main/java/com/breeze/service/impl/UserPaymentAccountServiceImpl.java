package com.breeze.service.impl;

import com.breeze.mapper.UserPaymentAccountMapper;
import com.breeze.pojo.UserPaymentAccount;
import com.breeze.pojo.bo.UserPaymentAccountBO;
import com.breeze.service.IUserPaymentAccountService;
import com.breeze.util.GetNameOfBankUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class UserPaymentAccountServiceImpl implements IUserPaymentAccountService {

    @Resource
    private UserPaymentAccountMapper userPaymentAccountMapper;

    /**
     * 根据用户Id删除指定账户
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return userPaymentAccountMapper.deleteById(id);
    }

    /**
     * 新增账户
     *
     * @param record
     * @return
     */
    @Override
    public boolean add(UserPaymentAccount record) {
        UserPaymentAccount userPaymentAccount = userPaymentAccountMapper.findByUserIdAndUserPaymentId(record.getUserId(), record.getPaymentTypeId().longValue());
        if (userPaymentAccount != null){
            userPaymentAccountMapper.deleteByUserIdAndPaymentTypeId(record.getUserId() , record.getPaymentTypeId().longValue());
            userPaymentAccountMapper.add(record);
            return true;
        }else {
            userPaymentAccountMapper.add(record);
            return true;
        }
    }

    /**
     * 根据账户Id查询账户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserPaymentAccount findById(Long id) {
        return userPaymentAccountMapper.findById(id);
    }

    /**
     * 修改账户信息
     *
     * @param record
     * @return
     */
    @Override
    public int update(UserPaymentAccount record) {
        return userPaymentAccountMapper.update(record);
    }

    /**
     * 查询全部用户的账户
     *
     * @return
     */
    @Override
    public List<UserPaymentAccount> findAll() {
        return userPaymentAccountMapper.findAll();
    }

    /**
     * 根据用户Id查询账户
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserPaymentAccountBO> findByUserId(Long userId) {
        List<UserPaymentAccountBO> userPaymentAccountBOS = userPaymentAccountMapper.findByUserId(userId);
        for (UserPaymentAccountBO userPaymentAccountBO : userPaymentAccountBOS) {
            if (userPaymentAccountBO.getPaymentTypeId().equals(1)){
                String nameOfBank = GetNameOfBankUtils.getNameOfBank(userPaymentAccountBO.getAccountNo());
                userPaymentAccountBO.setBankName(nameOfBank);
            }
        }
        return userPaymentAccountBOS ;
    }

}
