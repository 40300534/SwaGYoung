package com.breeze.controller;

import com.breeze.annotation.Admin;
import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.UserAddress;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IUserAddressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tx
 */
@RestController
@RequestMapping(value = "address")
@Api(tags = {"用户地址管理"})
public class UserAddressController {

    @Autowired
    private IUserAddressService userAddressService;


    /**
     * 获取所有用户地址
     */
    @Admin
    @LoginRequired
    @ApiOperation("获取所有用户地址")
    @GetMapping(value = "/findAll")
    public PageInfo<UserAddress> getAddressList(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userAddressService.findAll());
    }


    /**
     * 通过用户Id查询
     */
    //@LoginRequired
    @ApiOperation("通过用户Id查询")
    @GetMapping(value = "/userId")
    public PageInfo<UserAddress> findByUserId(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userAddressService.findByUserId(userId));
    }

    /**
     * 根据地址id查询地址
     *
     * @param id 地址Id
     * @return
     */
    @ApiOperation("根据地址Id查询地址")
    @GetMapping
    public UserAddress findById(@RequestParam("id") Long id) {
        UserAddress address=null;
        try {
             address = userAddressService.findById(id);
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
            return address;
    }

    /**
     * 添加地址
     */
    @ApiOperation("添加地址")
    @PostMapping
    public int add(@RequestBody UserAddress userAddress) {
        try {
            userAddressService.add(userAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAddress.getId().intValue();
    }

    /**
     * 删除地址
     */
    @ApiOperation("删除地址")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            userAddressService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改地址
     */
    @ApiOperation("修改地址")
    @PutMapping
    public ResultVO update(@RequestBody UserAddress userAddress) {

        try {
            userAddressService.update(userAddress);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }

    }

}
