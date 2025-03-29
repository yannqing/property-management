package com.qcx.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcx.property.domain.dto.auth.RegisterDto;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.entity.Message;
import com.qcx.property.domain.entity.Room;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.MessageContent;
import com.qcx.property.domain.model.RoomModel;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.AuthService;
import com.qcx.property.service.MessageService;
import com.qcx.property.service.RoleUserService;
import com.qcx.property.service.RoomService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description: 认证业务逻辑
 * @author: yannqing
 * @create: 2025-01-14 16:33
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleUserService roleUserService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MessageService messageService;

    @Resource
    private RoomService roomService;

    /**
     * 注册
     * @param registerDto
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        String username = Optional.ofNullable(registerDto.getUsername())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        String password = Optional.ofNullable(registerDto.getPassword())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        String nickName = Optional.ofNullable(registerDto.getNickName())
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // username 必须不能重复，且大于 6 个字符，小于等于 15 个字符
        if (username.length() <= 6 || username.length() > 15) {
            throw new BusinessException(ErrorType.USERNAME_LENGTH_ERROR);
        }
        boolean isUsernameExist = userMapper.exists(new QueryWrapper<User>().eq("username", username));
        if (isUsernameExist) {
            throw new BusinessException(ErrorType.USERNAME_ALREADY_EXIST);
        }

        // password 必须大于等于 8 位，小于 15 位
        if (password.length() < 8 || password.length() >= 15) {
            throw new BusinessException(ErrorType.PASSWORD_LENGTH_ERROR);
        }

        User registerUser = RegisterDto.dtoToUser(registerDto);
        registerUser.setPassword(passwordEncoder.encode(password));

        // 插入新注册用户
        int result = 1;
        log.info("用户user{}注册成功", registerUser.getUsername());

        // 给用户添加角色：不能添加管理员
        if (registerDto.getRoleId() == null) {
            result = userMapper.insert(registerUser);
            roleUserService.addRole(username, RoleType.USER);
        } else {
            // 管理员无法注册
            if (registerDto.getRoleId().equals(RoleType.ADMIN.getRoleId())) {
                throw new BusinessException(ErrorType.SYSTEM_ERROR);
            }

            // 如果是业主注册
            if (registerDto.getRoleId().equals(RoleType.OTHER.getRoleId())) {
                // 房产证信息这些不能为空
                if (registerDto.getRoomModel() == null) {
                    throw new BusinessException(ErrorType.ARGS_NOT_NULL);
                }

                if (registerDto.getRoomModel().getRoomCertificate() == null || registerDto.getRoomModel().getIDCard() == null || registerDto.getRoomModel().getBankName() == null || registerDto.getRoomModel().getBankCardNum() == null) {
                    throw new BusinessException(ErrorType.ARGS_NOT_NULL);
                }
                // 发送给管理员信息通知
                AddMessageDto addMessageDto = new AddMessageDto();
                addMessageDto.setType(MessageType.CHECK.getId());
                MessageContent<User> messageContent = new MessageContent<>();
                messageContent.setData(registerUser);
                messageContent.setRoomModel(registerDto.getRoomModel());
                messageContent.setNotify("您有一条业主注册申请待审批，请尽快审批");
                addMessageDto.setContent(JSON.toJSONString(messageContent));
                addMessageDto.setReceiveUser(1);

                messageService.addMessage(addMessageDto);

            } else {
                // 普通用户
                result = userMapper.insert(registerUser);
                roleUserService.addRole(username, registerDto.getRoleId());
                // 信息通知
                AddMessageDto addMessageDto = new AddMessageDto();
                addMessageDto.setType(MessageType.SYSTEM.getId());
                addMessageDto.setContent("恭喜您成为租客，欢迎您首次登录系统！");
                addMessageDto.setReceiveUser(registerUser.getUserId());

                messageService.addMessage(addMessageDto);
            }
        }

        log.info("用户{}添加角色{}成功", registerUser.getUsername(), RoleType.USER.getRoleId() == registerDto.getRoleId() ? RoleType.USER.getRoleCode() : RoleType.OTHER.getRoleCode());

        return result > 0;
    }

    @Override
    public boolean checkRegister(Integer id, boolean isPass, String roomNum) {
        if (id == null || roomNum == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        Message message = messageService.getById(id);
        if (message == null) {
            throw new BusinessException(ErrorType.MESSAGE_NOT_EXIST);
        }
        Room room = roomService.getOne(new QueryWrapper<Room>().eq("num", roomNum));
        if (room == null) {
            throw new BusinessException(ErrorType.ROOM_NOT_EXIST);
        }

        // 获取消息内容
        String content = message.getContent();
        MessageContent messageContent = JSON.parseObject(content, MessageContent.class);
        RoomModel roomModel = messageContent.getRoomModel();
        User registerUser = (User) messageContent.getData();
        if (isPass) {
            // 注册 + 新增角色
            userMapper.insert(registerUser);
            roleUserService.addRole(registerUser.getUserId(), RoleType.OTHER.getRoleId());
            // 设置房屋的业主以及其他信息
            room.setOwner(registerUser.getUserId());
            room.setDescription(JSON.toJSONString(roomModel));
            room.setStatus(1);
            roomService.updateById(room);
            // 信息通知
            AddMessageDto addMessageDto = new AddMessageDto();
            addMessageDto.setType(MessageType.SYSTEM.getId());
            addMessageDto.setContent("恭喜您成为业主，欢迎您首次登录系统！");
            addMessageDto.setReceiveUser(registerUser.getUserId());

            messageService.addMessage(addMessageDto);
            // TODO 信息通知
        } else {
            // TODO 信息通知
        }


        return true;
    }
}
