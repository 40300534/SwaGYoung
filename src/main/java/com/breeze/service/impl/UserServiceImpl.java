package com.breeze.service.impl;

import com.breeze.dao.UserDao;
import com.breeze.exception.MyException;
import com.breeze.mapper.UserMapper;
import com.breeze.pojo.User;
import com.breeze.service.UserService;
import com.breeze.util.DateUtils;
import com.breeze.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDao userDao;


    @Override
    public void register(User user) {
        if(user!=null){
            if(!StringUtils.isEmpty(user.getName())){
                User flag = userMapper.findUserName(user.getName());
                if(!StringUtils.isEmpty(flag)){
                    throw new MyException("请重新注册 用户名已存在");
                }else {
                    user.setId(UUIDUtils.getUUID());
                    user.setCreateTime(DateUtils.getDay());
                    userDao.save(user);
                }
            }
        }
    }

    @Override
    public User login(User user) {
        if(StringUtil.isNotEmpty(user.getName()) && StringUtil.isNotEmpty(user.getPassword())){
           User flag =  userMapper.findUserAndPasswrod(user.getName(),user.getPassword());
           if(!StringUtils.isEmpty(flag)){
               return flag;
           }else {
               throw new MyException("用户名或密码错误");
           }
        }
        return null;
    }


    @Override
    public User findById(String id) {
        User user = userMapper.findBy(id);
        return user;
    }

    @Override
    public void update(User user) {
        userDao.save (user);
    }

    @Override
    public PageInfo<User> findAll(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> all = userMapper.findAll();
        return new PageInfo<>(all);
    }

    @Override
    public void del(String ids) {
        if(ids.contains(",")){
            userMapper.del(ids);
        }else {
            userDao.deleteById(ids);
        }
    }
}
