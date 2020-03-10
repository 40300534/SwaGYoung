package com.breeze.service;

import com.breeze.pojo.User;
import com.breeze.pojo.bo.UserInfoBO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    User login(String phone,String password);

    User findByPhone(String phone);

    User findById(Long id);

    void update(User user);

    PageInfo<User> findAll(int pageNum,int pageSize);

    void del(String ids);

    boolean updateAvatar(MultipartFile imageFile, Long userId);

    Integer delete(Long userId);

    List<User> findByName(String name);

    UserInfoBO findUserInfo(Long userId);

    Integer updatePhone(Long userId, String phone);

    Integer updatePassword(Long userId, String newPassword);

    boolean findOldPassword(Long userId, String oldPassword);
}
