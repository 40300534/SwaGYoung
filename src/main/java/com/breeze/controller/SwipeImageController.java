package com.breeze.controller;

import com.breeze.pojo.SwipeImage;
import com.breeze.service.ISwipeImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wdnnccey
 * @date 2019-06-25 11:36
 * @Description: 轮播图
 */
@RestController
@RequestMapping(value = "/swipeImages")
@Api(tags = {"轮播图管理"})
public class SwipeImageController {

    @Autowired
    private ISwipeImageService swipeImageService;

    /**
     * 查询所有使用的轮播图
     *
     * @return
     */
    @ApiOperation("查询所有轮播图")
    @GetMapping("/findAll")
    public List<SwipeImage> findAll() {
        return swipeImageService.findAll();
    }

    /**
     * 添加轮播图
     */
    @ApiOperation("添加轮播图")
    @PostMapping()
    public int add(@RequestBody SwipeImage swipeImage) {
        return swipeImageService.add(swipeImage);
    }

    /**
     * 删除轮播图
     */
    @ApiOperation("删除轮播图")
    @DeleteMapping(value = "/{id}")
    public int delete(@PathVariable("id") Integer id) {
        return swipeImageService.deleteById(id);
    }

    /**
     * 修改轮播图
     */
    @ApiOperation("修改轮播图")
    @PutMapping()
    public int update(@RequestBody SwipeImage swipeImage) {
        return swipeImageService.update(swipeImage);

    }


}
