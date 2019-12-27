package com.breeze.service.impl;

import com.breeze.mapper.MessageTypeMapper;
import com.breeze.pojo.MessageType;
import com.breeze.service.IMessageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class MessageTypeServiceImpl implements IMessageTypeService {

    @Autowired
    private MessageTypeMapper messageTypeMapper;

    /**
     * 查询全部消息分类
     *
     * @return
     */
    @Override
    public List<MessageType> findAll() {
        return messageTypeMapper.findAll();
    }

    /**
     * 根据Id查询消息类型
     *
     * @param id
     * @return
     */
    @Override
    public MessageType findById(Long id) {
        return messageTypeMapper.findById(id);
    }

    /**
     * 新增消息类型
     *
     * @param messageType
     * @return
     */
    @Override
    public int add(MessageType messageType) {
        return messageTypeMapper.add(messageType);
    }

    /**
     * 修改消息类型
     *
     * @param messageType
     * @return
     */
    @Override
    public int update(MessageType messageType) {
        return messageTypeMapper.update(messageType);
    }

    /**
     * 删除消息类型
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return messageTypeMapper.deleteById(id);
    }
}
