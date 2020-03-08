package com.breeze.service;

import com.breeze.pojo.User;
import com.github.pagehelper.PageInfo;

public interface UserService {
    void register(User user);

    User login(User user);

    User findById(String id);

    void update(User user);

    PageInfo<User> findAll(int pageNum,int pageSize);

    void del(String ids);
}
