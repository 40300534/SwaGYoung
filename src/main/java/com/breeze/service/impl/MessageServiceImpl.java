package com.breeze.service.impl;

import com.breeze.mapper.MessageMapper;
import com.breeze.pojo.Message;
import com.breeze.pojo.bo.MessageBO;
import com.breeze.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询全部消息
     *
     * @return
     */
    @Override
    public List<Message> findAll() {
        return messageMapper.findAll();
    }

    /**
     * 根据收信者Id查询消息
     *
     * @param receiverId
     * @return
     */
    @Override
    public List<MessageBO> findByReceiverId(Long receiverId) {
        List<MessageBO> messageBOList = messageMapper.findByReceiverId(receiverId);
        List<MessageBO> messageBOS = messageMapper.findReadFlag();
        for (MessageBO messageBO : messageBOList) {
            for (MessageBO bo : messageBOS) {
                if(messageBO.getMessageTypeId().equals(bo.getMessageTypeId())){
                    messageBO.setUnreadMessageCount(bo.getUnreadMessageCount());
                }
            }

        }
        return messageBOList;

    }

    /**
     * 根据发信者Id查询消息
     *
     * @param senderId
     * @return
     */
    @Override
    public List<Message> findBySenderId(Long senderId) {
        List<Message> messageList = messageMapper.findBySenderId(senderId);
        for (Message message : messageList) {
            message.setReadFlag(1);
            messageMapper.update(message);
        }
        return messageList;
    }

    /**
     * 根据Id查询消息
     *
     * @param id
     * @return
     */
    @Override
    public Message findById(Long id) {
        return messageMapper.findById(id);
    }

    /**
     * 新增消息
     *
     * @param message
     * @return
     */
    @Override
    public int add(Message message) {
        message.setCreateTime(new Date());
        return messageMapper.add(message);
    }

    /**
     * 修改消息
     *
     * @param message
     * @return
     */
    @Override
    public int update(Message message) {
        message.setCreateTime(new Date());
        return messageMapper.update(message);
    }

    /**
     * 删除消息
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        return messageMapper.deleteById(id);
    }
}
