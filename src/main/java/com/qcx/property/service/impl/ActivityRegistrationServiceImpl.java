package com.qcx.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.communityActivity.AddActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.QueryActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.UpdateActivityRegistrationDto;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.MessageContent;
import com.qcx.property.domain.vo.communityActivity.ActivityRegistrationVo;
import com.qcx.property.domain.vo.communityActivity.CommunityActivityVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.enums.activity.ActivityRegistrationStatusType;
import com.qcx.property.enums.activity.CommunityActivityStatusType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.ActivityRegistrationService;
import com.qcx.property.mapper.ActivityRegistrationMapper;
import com.qcx.property.service.CommunityActivityService;
import com.qcx.property.service.MessageService;
import com.qcx.property.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【activity_registration(活动报名表)】的数据库操作Service实现
* @createDate 2025-02-14 17:09:54
*/
@Slf4j
@Service
public class ActivityRegistrationServiceImpl extends ServiceImpl<ActivityRegistrationMapper, ActivityRegistration>
    implements ActivityRegistrationService{

    @Resource
    private CommunityActivityService communityActivityService;

    @Resource
    private UserService userService;

    @Resource
    private MessageService messageService;

    @Override
    public Page<ActivityRegistrationVo> getAllActivityRegistrations(QueryActivityRegistrationDto queryActivityRegistrationDto) {
        // 判空
        Optional.ofNullable(queryActivityRegistrationDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryActivityRegistrationDto.getId();
        Integer activityId = queryActivityRegistrationDto.getActivityId();
        Integer userId = queryActivityRegistrationDto.getUserId();
        Integer status = queryActivityRegistrationDto.getStatus();
        Date registerTime = queryActivityRegistrationDto.getRegisterTime();
        Date checkInTime = queryActivityRegistrationDto.getCheckInTime();
        Integer participantCount = queryActivityRegistrationDto.getParticipantCount();


        QueryWrapper<ActivityRegistration> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(activityId!= null, "activityId", activityId);
        queryWrapper.eq(userId!= null, "userId", userId);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(registerTime!= null, "registerTime", registerTime);
        queryWrapper.eq(checkInTime!= null, "checkInTime", checkInTime);
        queryWrapper.eq(participantCount!= null, "participantCount", participantCount);
        log.info("查询所有活动报名");
        Page<ActivityRegistration> page = this.page(new Page<>(queryActivityRegistrationDto.getCurrent(), queryActivityRegistrationDto.getPageSize()), queryWrapper);
        List<ActivityRegistrationVo> activityRegistrationVoList = page.getRecords().stream().map(activityRegistration -> {
            ActivityRegistrationVo activityRegistrationVo = ActivityRegistrationVo.activityRegistrationToVo(activityRegistration);
            activityRegistrationVo.setActivity(CommunityActivityVo.communityActivityToVo(communityActivityService.getById(activityRegistration.getActivityId())));
            activityRegistrationVo.setUser(UserVo.objToVo(userService.getById(activityRegistration.getUserId())));
            activityRegistrationVo.setStatus(ActivityRegistrationStatusType.getRemarkById(activityRegistration.getStatus()));
            return activityRegistrationVo;
        }).toList();

        return new Page<ActivityRegistrationVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(activityRegistrationVoList);
    }

    @Override
    public boolean updateActivityRegistration(UpdateActivityRegistrationDto updateActivityRegistrationDto) {
        // 判空
        Optional.ofNullable(updateActivityRegistrationDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateActivityRegistrationDtoId = updateActivityRegistrationDto.getId();
        Optional.ofNullable(this.getById(updateActivityRegistrationDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.ACTIVITY_REGISTRATION_NOT_EXIST));

        ActivityRegistration updateActivityRegistration = UpdateActivityRegistrationDto.objToActivityRegistration(updateActivityRegistrationDto);

        boolean updateResult = this.updateById(updateActivityRegistration);
        log.info("更新活动报名");


        return updateResult;
    }

    @Override
    public boolean addActivityRegistration(AddActivityRegistrationDto addActivityRegistrationDto) {
        // 判空
        if (addActivityRegistrationDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        if (addActivityRegistrationDto.getActivityId() == null) {
            throw new BusinessException(ErrorType.ACTIVITY_REGISTRATION_ACTIVITY_ID_NOT_NULL);
        }
        if (addActivityRegistrationDto.getUserId() == null) {
            throw new BusinessException(ErrorType.ACTIVITY_REGISTRATION_USER_ID_NOT_NULL);
        }

        // 有效性判断
        CommunityActivity communityActivity = communityActivityService.getById(addActivityRegistrationDto.getActivityId());
        User registerUser = userService.getById(addActivityRegistrationDto.getUserId());

        Optional.ofNullable(communityActivity)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(registerUser)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 如果活动不是可报名的状态，抛异常
        if (!communityActivity.getStatus().equals(CommunityActivityStatusType.REGISTRATION.getId())) {
            throw new BusinessException(ErrorType.COMMUNITY_ACTIVITY_NOT_IN_REGISTRATION);
        }

        // 如果要报名的人数 > 活动剩余的可报名人数，抛异常
        if (addActivityRegistrationDto.getParticipantCount() != null && addActivityRegistrationDto.getParticipantCount() > (communityActivity.getMaxParticipants() - communityActivity.getCurrentParticipants())) {
            throw new BusinessException(ErrorType.COMMUNITY_ACTIVITY_NO_ENOUGH_PLACE);
        }

        // 添加活动报名
        ActivityRegistration addActivityRegistration = AddActivityRegistrationDto.objToActivityRegistration(addActivityRegistrationDto);
        boolean saveResult = this.save(addActivityRegistration);
        log.info("新增活动报名");

        // 发送通知
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.COMMUNITY_ACTIVITY.getId());

        MessageContent<CommunityActivity> addMessageContent = new MessageContent<>();
        addMessageContent.setData(communityActivity);
        addMessageContent.setNotify("您已成功报名" + communityActivity.getTitle() + "活动，请准时参加");
        addMessageDto.setContent(JSON.toJSONString(addMessageContent));
        addMessageDto.setReceiveUser(registerUser.getUserId());
        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (sendMessageResult) {
            log.info("用户 id：{} 报名活动成功，活动 id：{}，消息推送成功", registerUser.getUserId(), communityActivity.getId());
        } else {
            log.info("用户 id：{} 报名活动成功，活动 id：{}，消息推送失败", registerUser.getUserId(), communityActivity.getId());
        }

        return saveResult;
    }

    @Override
    public boolean deleteActivityRegistration(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.ACTIVITY_REGISTRATION_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除活动报名 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchActivityRegistration(Integer... activityRegistrationIds) {
        // 判空
        if (activityRegistrationIds == null || activityRegistrationIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<ActivityRegistration> activityRegistrationList = this.listByIds(Arrays.asList(activityRegistrationIds));
        if (activityRegistrationList.size()!= activityRegistrationIds.length) {
            throw new BusinessException(ErrorType.ACTIVITY_REGISTRATION_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(activityRegistrationIds));
        log.info("批量删除活动报名");

        return deleteResult > 0;
    }
}




