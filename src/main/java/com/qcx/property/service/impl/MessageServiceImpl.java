package com.qcx.property.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.message.QueryApprovalDto;
import com.qcx.property.domain.dto.message.QueryMessageDto;
import com.qcx.property.domain.dto.message.UpdateMessageDto;
import com.qcx.property.domain.entity.Message;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.ApprovalModel;
import com.qcx.property.domain.model.MessageContent;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.message.MessageVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.MessageService;
import com.qcx.property.mapper.MessageMapper;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* @author 67121
* @description 针对表【message(消息通知表)】的数据库操作Service实现
* @createDate 2025-02-14 17:09:37
*/
@Slf4j
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Resource
    private UserMapper userMapper;

    @Override
    public Page<Message> getAllMessages(QueryMessageDto queryMessageDto) {
        // 判空
        Optional.ofNullable(queryMessageDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer type = queryMessageDto.getType();
        String content = queryMessageDto.getContent();
        Integer sendUser = queryMessageDto.getSendUser();
        Integer receiveUser = queryMessageDto.getReceiveUser();
        Integer status = queryMessageDto.getStatus();
        Date createTime = queryMessageDto.getCreateTime();
        Date updateTime = queryMessageDto.getUpdateTime();

        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.like(content!= null, "content", content);
        queryWrapper.eq(createTime!= null, "createTime", createTime);
        queryWrapper.eq(updateTime!= null, "updateTime", updateTime);
        queryWrapper.eq(sendUser!= null, "sendUser", sendUser);
        queryWrapper.eq(receiveUser!= null, "receiveUser", receiveUser);
        queryWrapper.eq(status!= null, "description", status);
        log.info("查询所有消息通知");

        return this.page(new Page<>(queryMessageDto.getCurrent(), queryMessageDto.getPageSize()), queryWrapper);
    }

    @Override
    public Page<MessageVo> getMyselfMessages(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 查询所有信息
        List<Message> allMessageInfo = this.baseMapper.selectList(new QueryWrapper<Message>().eq("receiveUser", loginUser.getUserId()));

        // 二次封装
        List<MessageVo> messageVoList = allMessageInfo.stream().map(message -> {
            MessageVo messageVo = MessageVo.objToVo(message);
            messageVo.setSendUser(UserVo.objToVo(userMapper.selectById(message.getSendUser())));
            messageVo.setReceiveUser(UserVo.objToVo(userMapper.selectById(message.getReceiveUser())));
            messageVo.setType(MessageType.getRemarkById(message.getType()));

            return messageVo;
        }).toList();
        log.info("查询所有消息通知(个人)");

        return new Page<MessageVo>(pageRequest.getCurrent(), pageRequest.getPageSize()).setRecords(messageVoList);
    }

    @Override
    public boolean updateMessage(UpdateMessageDto updateMessageDto) {
        // 判空
        Optional.ofNullable(updateMessageDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateMessageId = updateMessageDto.getId();
        Optional.ofNullable(this.getById(updateMessageId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        Message updateMessage = UpdateMessageDto.objToMessage(updateMessageDto);

        boolean updateResult = this.updateById(updateMessage);
        log.info("更新消息通知");

        return updateResult;
    }

    @Override
    public boolean addMessage(AddMessageDto addMessageDto) {
        // 判空
        // 消息内容不能为空
        Optional.ofNullable(addMessageDto.getContent())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //接收者id 不能为空
        Optional.ofNullable(addMessageDto.getReceiveUser())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 添加消息通知
        Message message = AddMessageDto.objToMessage(addMessageDto);
        boolean saveResult = this.save(message);
        log.info("新增消息通知");

        return saveResult;
    }

    @Override
    public boolean deleteMessage(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.MESSAGE_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除消息通知（id：{}）", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchMessage(Integer[] messageIds) {
        // 判空
        if (messageIds == null || messageIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Message> messageList = this.listByIds(Arrays.asList(messageIds));
        if (messageList.size()!= messageIds.length) {
            throw new BusinessException(ErrorType.MESSAGE_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(messageIds));
        log.info("批量删除消息通知");

        return deleteResult > 0;
    }

    @Override
    public boolean readMessage(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        Message message = this.getById(id);
        if (message == null) {
            throw new BusinessException(ErrorType.MESSAGE_NOT_EXIST);
        }

        // 已读
        boolean result = this.update(new UpdateWrapper<Message>().eq("id", id).set("status", 1));
        log.info("修改消息状态为已读");
        return result;
    }

    @Override
    public Page<ApprovalModel> getApprovals(QueryApprovalDto queryApprovalDto) {

        Integer status = queryApprovalDto.getStatus();
        String nickname = queryApprovalDto.getNickname();
        String username = queryApprovalDto.getUsername();
        int current = queryApprovalDto.getCurrent();
        int pageSize = queryApprovalDto.getPageSize();


        LambdaQueryWrapper<Message> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getType, MessageType.CHECK.getId());

        queryWrapper.eq(status != null, Message::getStatus, status);


        List<Message> messages = this.getBaseMapper().selectList(queryWrapper);
        List<ApprovalModel> approvalModels = new ArrayList<>();

        if (!nickname.isEmpty() || !username.isEmpty()) {
            messages.forEach(message -> {
                String content = message.getContent();
                if (content != null && !content.isEmpty()) {
                    MessageContent<User> messageContent = JSON.parseObject(content, new TypeReference<>() {
                    });
                    User user = messageContent.getData();
                    if (user == null) {
                        throw new BusinessException(ErrorType.SYSTEM_ERROR);
                    } else {
                        // 查询条件不为空 符合要求的 user
                        if ((!nickname.isEmpty() && user.getNickName().equals(nickname)) || (!username.isEmpty() && user.getUsername().equals(username))) {
                            ApprovalModel approvalModel = new ApprovalModel();
                            approvalModel.setUser(user);
                            approvalModel.setRoomModel(messageContent.getRoomModel());
                            approvalModel.setNotify(messageContent.getNotify());
                            approvalModels.add(approvalModel);
                        }
                    }
                } else {
                    throw new BusinessException(ErrorType.SYSTEM_ERROR);
                }
            });
        } else {
            // 无查询条件，直接获取
            messages.forEach(message -> {
                String content = message.getContent();
                if (content != null && !content.isEmpty()) {
                    MessageContent<User> messageContent = JSON.parseObject(content, new TypeReference<>() {
                    });
                    User user = messageContent.getData();
                    if (user == null) {
                        throw new BusinessException(ErrorType.SYSTEM_ERROR);
                    } else {
                        ApprovalModel approvalModel = new ApprovalModel();
                        approvalModel.setUser(user);
                        approvalModel.setRoomModel(messageContent.getRoomModel());
                        approvalModel.setNotify(messageContent.getNotify());
                        approvalModels.add(approvalModel);
                    }
                } else {
                    throw new BusinessException(ErrorType.SYSTEM_ERROR);
                }
            });
        }

        // 计算起始索引
        int start = (current - 1) * pageSize;
        // 计算结束索引(不包含)
        int end = Math.min(start + pageSize, approvalModels.size());

        List<ApprovalModel> approvalModelList = IntStream.range(start, end)
                .mapToObj(approvalModels::get)
                .toList();

        return new Page<ApprovalModel>(current, pageSize, approvalModels.size()).setRecords(approvalModelList);
    }
}




