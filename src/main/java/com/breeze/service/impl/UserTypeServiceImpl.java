package com.breeze.service.impl;

import com.breeze.mapper.DepositMapper;
import com.breeze.mapper.FeeMapper;
import com.breeze.mapper.UserTypeMapper;
import com.breeze.pojo.UserType;
import com.breeze.pojo.dto.UserTypeDTO;
import com.breeze.service.IUserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tx
 */
@Service
public class UserTypeServiceImpl implements IUserTypeService {

    @Autowired
    private UserTypeMapper userTypeMapper;

    @Autowired
    private FeeMapper feeMapper;

    @Autowired
    private DepositMapper depositMapper;

    /**
     * @return 查所有
     */
    @Override
    public List<UserType> findAll() {
        return userTypeMapper.findAll();
    }

    /**
     * @param id 根据id查询
     * @return
     */
    @Override
    public UserType findById(Long id) {
        return userTypeMapper.findById(id);
    }

    /**
     * 添加
     *
     * @param userType
     * @return
     */
    @Override
    public int add(UserType userType) {
        return userTypeMapper.add(userType);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return userTypeMapper.deleteById(id);
    }

    /**
     * 修改
     *
     * @param userType
     * @return
     */
    @Override
    public int update(UserType userType) {
        return userTypeMapper.update(userType);
    }

    @Override
    public List<UserTypeDTO> findRole() {
        List<UserType> userTypeList = userTypeMapper.findAll();
        List<UserTypeDTO> dtoList = new ArrayList<>();
        userTypeList.forEach(userType -> {
            UserTypeDTO userTypeDTO = new UserTypeDTO();
            userTypeDTO.setId(userType.getId());
            userTypeDTO.setName(userType.getName());
            userTypeDTO.setFee(feeMapper.findFeeByUserTypeId(userType.getId()));
            userTypeDTO.setDeposit(depositMapper.findDepositByUserTypeId(userType.getId()));
            dtoList.add(userTypeDTO);
        });
        return dtoList;
    }
}
