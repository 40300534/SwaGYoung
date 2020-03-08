package com.breeze.controller;


import com.breeze.exception.MyException;
import com.breeze.pojo.User;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@Api(value = "用户",description ="用户" )
public class UserController {

    @Autowired
    private UserService userService;

//顾客的注册、登录模块、

    /**
     * 注册
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ApiOperation("注册")
    public ResultVO register(@RequestBody User user){
        try {
            userService.register(user);
        } catch (MyException e) {
            e.printStackTrace();
            return new ResultVO(false,500,e.getMessage());
        }
        return new ResultVO(true,200,"SUCCESS");

    }

    /**
     * 展示用户列表
     *
     */
    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}",method = RequestMethod.GET)
    @ApiOperation("展示用户数据")
    public ResultVO findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        PageInfo<User> pageInfo   = userService .findAll(pageNum,pageSize);
        return new ResultVO(true,200,"SUCCESS",pageInfo);
    }

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation("登录")
    public ResultVO login(@RequestBody User user){
        User flag =  userService.login(user);
        return new ResultVO(true,200,"SUCCESS",flag);
    }

    @RequestMapping(value = "/del/{ids}",method = RequestMethod.GET)
    @ApiOperation("删除/批量")
    public ResultVO del(@PathVariable("ids") String ids){
        userService.del(ids);
        return new ResultVO(true,200,"SUCCESS");
    }




    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    @ApiOperation("查询个人信息")
    public ResultVO findById(@PathVariable("id") String id){
         User user = userService.findById(id);
         return new ResultVO(true,200,"SUCCESS",user);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation("修改信息")
    public ResultVO update(@RequestBody User user){
        userService.update(user);
        return new ResultVO(true,200,"SUCCESS");
    }

}
