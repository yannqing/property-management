package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.message.QueryMessageDto;
import com.qcx.property.domain.dto.message.UpdateMessageDto;
import com.qcx.property.domain.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.message.MessageVo;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 67121
* @description 针对表【message(消息通知表)】的数据库操作Service
* @createDate 2025-02-14 17:09:37
*/
public interface MessageService extends IService<Message> {

    Page<Message> getAllMessages(QueryMessageDto queryMessageDto);

    Page<MessageVo> getMyselfMessages(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException;

    boolean updateMessage(UpdateMessageDto updateMessageDto);

    boolean addMessage(AddMessageDto addMessageDto);

    boolean deleteMessage(Integer id);

    boolean deleteBatchMessage(Integer[] messageIds);
}
