package com.breeze.service;

import com.breeze.pojo.User;
import com.breeze.pojo.bo.UserInfoBO;
import com.breeze.pojo.vo.ResultVO;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 *  @author XiaoCi
 */
public interface IUserService {


    /**
     * 查询全部列表
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return user
     */
    User login(String phone, String password);

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 通过用户id查询用户
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 修改用户头像
     * @param profile
     * @param userId
     * @return
     */
    boolean updateAvatar(MultipartFile profile , Long userId);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer delete(Long userId);

    /**
     * 姓名查找用户
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
     * 用户修改手机号
     * @param phone
     * @return
     */
    Integer updatePhone(Long userId, String phone);

    /**
     * 修改用户密码
     * @param newPassword
     * @return
     */
    Integer updatePassword(Long userId,String newPassword);

    /**
     * 查找旧密码
     * @param userId
     * @param oldPassword
     * @return
     */
    boolean findOldPassword(Long userId, String oldPassword);
}
