package com.breeze.controller;

import com.breeze.annotation.LoginRequired;
import com.breeze.pojo.UserType;
import com.breeze.pojo.dto.UserTypeDTO;
import com.breeze.service.IUserTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tx
 */
@RestController
@RequestMapping(value = "/type")
@Api(tags = {"UserType管理"})
public class UserTypeController {

    @Autowired
    private IUserTypeService userTypeService;

    /**
     * 获取所有用用户类型
     */
    @ApiOperation("获取所有用户类型")
    @LoginRequired
    @GetMapping
    public List<UserType> getUserTypeList() {
        return userTypeService.findAll();
    }

    /**
     * 通过用户类型ID查询
     */
    @ApiOperation("通过用户类型ID查询")
    @GetMapping(value = "/{id}")
    public UserType findById(@PathVariable("id") Long id) {
        return userTypeService.findById(id);
    }

    /**
     * 添加用户类型
     */
    @ApiOperation("添加用户类型")
    @PostMapping
    public int add(@RequestBody UserType userType) {
        return userTypeService.add(userType);
    }

    /**
     * 删除用户类型
     */
    @ApiOperation("删除用户类型")
    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable("id") Long id) {
        return userTypeService.deleteById(id);
    }

    /**
     * 修改用户类型
     */
    @ApiOperation("修改用户类型")
    @PutMapping
    public int update(@RequestBody UserType userType) {
        return userTypeService.update(userType);
    }

    /**
     * 角色管理
     */
    @ApiOperation("角色管理")
    @GetMapping(value = "/findRole")
    public List<UserTypeDTO> findRole() {
        return userTypeService.findRole();
    }
}
