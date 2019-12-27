package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.MessageType;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IMessageTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/messageType")
@Api(tags = {"消息类型管理"})
public class MessageTypeController {

    @Autowired
    private IMessageTypeService messageTypeService;

    /**
     * 查询全部消息类型
     *
     * @return
     */
    @ApiOperation("查询全部消息类型")
    @GetMapping(value = "/findAll")
    public List<MessageType> findAll() {
        return messageTypeService.findAll();
    }

    /**
     * 根据Id查询消息类型
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询消息类型")
    @GetMapping
    public MessageType findById(@RequestParam("id") Long id) {
        return messageTypeService.findById(id);
    }

    /**
     * 新增消息类型
     *
     * @param messageType
     * @return
     */
    @ApiOperation("新增消息类型")
    @PostMapping
    public ResultVO add(@RequestBody MessageType messageType) {
        try {
            messageTypeService.add(messageType);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改消息类型
     *
     * @param messageType
     * @return
     */
    @ApiOperation("修改消息类型")
    @PutMapping
    public ResultVO update(@RequestBody MessageType messageType) {
        try {
            messageTypeService.update(messageType);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除指定消息类型
     *
     * @param id
     * @return
     */
    @ApiOperation("删除指定消息类型")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            messageTypeService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }
}
