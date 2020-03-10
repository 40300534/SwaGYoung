package com.breeze.controller;


import com.alibaba.fastjson.JSONObject;
import com.breeze.annotation.Admin;
import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.exception.MyException;
import com.breeze.pojo.User;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.UserService;
import com.breeze.util.RedisLoginUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
@Api(value = "用户",description ="用户" )
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisLoginUtils redisLoginUtil;

    @Autowired
    private BCryptPasswordEncoder encoder;
    /**
     * 后台新增用户
     * @param user
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping(value = "/add")
    public ResultVO add(@RequestBody User user) {
        try {
            User byPhone = userService.findByPhone(user.getPhone());
            if (byPhone == null) {
                userService.add(user);
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "添加成功");
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "该手机号已经被绑定啦，请您更换其他手机号再进行注册。");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器开小差了 暂时比较忙");
        }
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
     * 根据ID查询
     *
     * @param id ID
     *           return
     */
    @ApiOperation("通过ID查询用户")
    @PostMapping(value = "/{id}")
    @LoginRequired
    public ResultVO findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            user.setPassword("隐藏密码");
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);

        }
    }
    /**
     * 用户登录
     *
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("phone") String phone,
                          @RequestParam("password") String password) {
        JSONObject result = new JSONObject();
        try {
            User login = userService.login(phone, password);
            if (login.getName() != null) {
                String token = (String) redisLoginUtil.get(String.valueOf(login.getId()));
                result.put("status", "登录成功");
                result.put("token", token);
                result.put("UserId", login.getId());
                result.put("displayName", login.getDisplayName());
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "登录成功", result);
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "你输入的密码和账户名不匹配，是否忘记密码或忘记会员名");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }

    }
    @RequestMapping(value = "/del/{ids}",method = RequestMethod.GET)
    @ApiOperation("删除/批量")
    public ResultVO del(@PathVariable("ids") String ids){
        userService.del(ids);
        return new ResultVO(true,200,"SUCCESS");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation("修改信息")
    public ResultVO update(@RequestBody User user){
        userService.update(user);
        return new ResultVO(true,200,"SUCCESS");
    }

}
