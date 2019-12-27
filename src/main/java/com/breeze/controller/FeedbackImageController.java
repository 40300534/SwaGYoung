package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.FeedbackImage;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IFeedbackImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by wdnnccey
 * @date 2019-08-01 08:55
 * @Description: 意见反馈图片
 */
@RestController
@RequestMapping(value = "/feedbackImage")
@Api(tags = "意见反馈-图片")
public class FeedbackImageController {

    @Autowired
    private IFeedbackImageService feedbackImageService;

    /**
     * 获取全部意见反馈图片
     */
    @ApiOperation("获取全部意见反馈图片")
    @GetMapping(value = "/findAll")
    public PageInfo<FeedbackImage> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(feedbackImageService.findAll());
    }


    /**
     * 根据Id获取意见反馈图片
     */
    @ApiOperation("根据ID获取意见反馈图片")
    @GetMapping(value = "/{id}")
    public FeedbackImage findById(@PathVariable("id") Long id) {
        return feedbackImageService.findById(id);
    }

    /**
     * 新增意见反馈图片
     */
    @ApiOperation("新增意见反馈图片")
    @PostMapping
    public ResultVO add(@RequestBody FeedbackImage feedbackType) {
        try {
            feedbackImageService.add(feedbackType);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改意见反馈图片
     */
    @ApiOperation("修改意见反馈图片")
    @PutMapping
    public ResultVO update(@RequestBody FeedbackImage feedbackImage) {
        try {
            feedbackImageService.update(feedbackImage);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除意见反馈图片
     */
    @ApiOperation("删除意见反馈图片")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            feedbackImageService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }
}
