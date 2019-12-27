package com.breeze.mapper;

import com.breeze.pojo.Message;
import com.breeze.pojo.bo.MessageBO;

import java.util.List;

/**
 * @author 莫奇
 */
public interface MessageMapper {

    /**
     * 查询全部消息
     * @return
     */
    List<Message> findAll();

    /**
     * 根据收信者Id查询消息
     * @param receiverId
     * @return
     */
    List<MessageBO> findByReceiverId(Long receiverId);

    /**
     * 根据发信者Id查询消息
     * @param senderId
     * @return
     */
    List<Message> findBySenderId(Long senderId);

    /**
     * 根据Id查询消息
     * @param id
     * @return
     */
    Message findById(Long id);

    /**
     * 新增消息
     * @param message
     * @return
     */
    int add(Message message);

    /**
     * 修改消息
     * @param message
     * @return
     */
    int update(Message message);

    /**
     * 根据ID删除消息
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 查询未读消息
     * @return
     */
    List<MessageBO> findReadFlag();
}
