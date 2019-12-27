package com.breeze.mapper;

import com.breeze.pojo.UserType;

import java.util.List;

/**
 * @author tx
 */
public interface UserTypeMapper {
    /**
     * @return 查所有
     */
    List<UserType> findAll();

    /**
     * @param id 根据id查
     * @return
     */
    UserType findById(Long id);

    /**
     * add插入
     * @param userType
     * @return
     */
    int add(UserType userType);

    /**
     * 删除deleteById
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 多条件修改
     * @param userType
     * @return
     */
    int update(UserType userType);


}
