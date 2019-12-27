package com.breeze.service;

import com.breeze.pojo.UserType;
import com.breeze.pojo.dto.UserTypeDTO;

import java.util.List;

/**
 * @author tx
 */
public interface IUserTypeService {
    /**
     * 查询所有
     * @return
     */
    List<UserType> findAll();

    /**
     * @param id 根据id查询
     * @return
     */
    UserType findById(Long id);

    /**
     * 添加用户类型
     * @param userType
     * @return
     */
    int add(UserType userType);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**修改
     *
     * @param userType
     * @return
     */
    int update(UserType userType);

    /**
     * 查找角色和费用
     * @return
     */
    List<UserTypeDTO> findRole();

    /**添加插入
     * @param userType
     * @return
     */
}
