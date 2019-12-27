package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.Message;
import com.breeze.pojo.bo.MessageBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/message")
@Api(tags = {"消息通知管理"})
public class MessageController {

    @Autowired
    private IMessageService messageService;

    /**
     * 查询全部消息
     *
     * @return 全部消息
     */
    @ApiOperation("查询全部消息")
    @GetMapping(value = "/findAll")
    public PageInfo<Message> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(messageService.findAll());
    }

    /**
     * 根据收信者Id查询消息
     *
     * @param receiverId
     * @return
     */
    @ApiOperation("根据收信者Id查询消息")
    @GetMapping(value = "/receiverId")
    public PageInfo<MessageBO> findByReceiverId(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("receiverId") Long receiverId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(messageService.findByReceiverId(receiverId));
    }

    /**
     * 根据发信者Id查询消息
     *
     * @param senderId
     * @return
     */
    @ApiOperation("根据发信者Id查询消息")
    @GetMapping(value = "/senderId")
    public List<Message> findBySenderId(@RequestParam("senderId") Long senderId) {
        return messageService.findBySenderId(senderId);
    }

    /**
     * 根据Id查询消息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询消息")
    @GetMapping
    public Message findById(@RequestParam("id") Long id) {
        return messageService.findById(id);
    }

    /**
     * 新增消息
     *
     * @param message
     * @return
     */
    @ApiOperation("新增消息")
    @PostMapping
    public ResultVO add(@RequestBody Message message) {
        try {
            messageService.add(message);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改消息
     *
     * @param message
     * @return
     */
    @ApiOperation("修改消息")
    @PutMapping
    public ResultVO update(@RequestBody Message message) {
        try {
            messageService.update(message);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 根据Id删除消息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id删除消息")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            messageService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }
}
