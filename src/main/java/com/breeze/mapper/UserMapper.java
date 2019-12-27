package com.breeze.mapper;

import com.breeze.pojo.User;
import com.breeze.pojo.bo.UserInfoBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author tx
 */
public interface UserMapper {

    /**
     * 查询所有用户
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     * 通过手机号查询用户
     * @param phone
     * @return User
     */
    User findByPhone(String phone);

    /**
     * 通过id查询用户
     * @param id
     * @return User
     */
    User findById(Long id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(@Param("user") User user);

    /**
     * 修改用户头像
     * @param user
     */
    int updateAvatar(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer delete(Long userId);

    /**
     * 查用户姓名的用户
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 用户登录信息
     * @param userId
     * @return
     */
    UserInfoBO findUserInfo(Long userId);

    /**
     * 修改手机号
     * @param phone
     * @return
     */
    Long updatePhone(Long userId,String phone);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    Long updatePassword(Long userId, String newPassword);

}
