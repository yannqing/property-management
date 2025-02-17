package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.communityActivity.AddCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.QueryCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.UpdateCommunityActivityDto;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.vo.communityActivity.CommunityActivityVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.enums.activity.CommunityActivityStatusType;
import com.qcx.property.enums.activity.CommunityActivityType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.ActivityRegistrationMapper;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.CommunityActivityService;
import com.qcx.property.mapper.CommunityActivityMapper;
import com.qcx.property.service.MessageService;
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
* @description 针对表【community_activity(社区活动表)】的数据库操作Service实现
* @createDate 2025-02-14 17:09:46
*/
@Slf4j
@Service
public class CommunityActivityServiceImpl extends ServiceImpl<CommunityActivityMapper, CommunityActivity>
    implements CommunityActivityService{

    @Resource
    private ActivityRegistrationMapper activityRegistrationMapper;

    @Resource
    private MessageService messageService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<CommunityActivityVo> getAllCommunityActivities(QueryCommunityActivityDto queryCommunityActivityDto) {
        // 判空
        Optional.ofNullable(queryCommunityActivityDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCommunityActivityDto.getId();
        String title = queryCommunityActivityDto.getTitle();
        String description = queryCommunityActivityDto.getDescription();
        Integer type = queryCommunityActivityDto.getType();
        Date startTime = queryCommunityActivityDto.getStartTime();
        Date endTime = queryCommunityActivityDto.getEndTime();
        String location = queryCommunityActivityDto.getLocation();
        Integer maxParticipants = queryCommunityActivityDto.getMaxParticipants();
        Integer currentParticipants = queryCommunityActivityDto.getCurrentParticipants();
        Integer organizer = queryCommunityActivityDto.getOrganizer();
        String contactPhone = queryCommunityActivityDto.getContactPhone();
        Integer status = queryCommunityActivityDto.getStatus();
        String coverImage = queryCommunityActivityDto.getCoverImage();
        Date signUpDeadline = queryCommunityActivityDto.getSignUpDeadline();


        QueryWrapper<CommunityActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(type!= null, "type", type);
        queryWrapper.eq(startTime!= null, "startTime", startTime);
        queryWrapper.eq(endTime!= null, "endTime", endTime);
        queryWrapper.eq(maxParticipants!= null, "maxParticipants", maxParticipants);
        queryWrapper.eq(currentParticipants!= null, "currentParticipants", currentParticipants);
        queryWrapper.like(title!= null, "title", title);
        queryWrapper.like(location!= null, "location", location);
        queryWrapper.like(contactPhone!= null, "contactPhone", contactPhone);
        queryWrapper.like(coverImage!= null, "coverImage", coverImage);
        queryWrapper.eq(organizer!= null, "organizer", organizer);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(organizer!= null, "organizer", organizer);
        queryWrapper.like(signUpDeadline!= null, "signUpDeadline", signUpDeadline);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有社区活动");

        Page<CommunityActivity> page = this.page(new Page<>(queryCommunityActivityDto.getCurrent(), queryCommunityActivityDto.getPageSize()), queryWrapper);

        List<CommunityActivityVo> communityActivityVoList = page.getRecords().stream().map(communityActivity -> {
            CommunityActivityVo communityActivityVo = CommunityActivityVo.communityActivityToVo(communityActivity);
            communityActivityVo.setOrganizer(UserVo.objToVo(userMapper.selectById(communityActivity.getOrganizer())));
            communityActivityVo.setStatus(CommunityActivityStatusType.getRemarkById(communityActivity.getStatus()));
            communityActivityVo.setType(CommunityActivityType.getRemarkById(communityActivity.getType()));
            return communityActivityVo;
        }).toList();

        return new Page<CommunityActivityVo>(queryCommunityActivityDto.getCurrent(), queryCommunityActivityDto.getPageSize(), page.getTotal()).setRecords(communityActivityVoList);
    }

    @Override
    public boolean updateCommunityActivity(UpdateCommunityActivityDto updateCommunityActivityDto) {
        // 判空
        Optional.ofNullable(updateCommunityActivityDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCommunityActivityDtoId = updateCommunityActivityDto.getId();
        Optional.ofNullable(this.getById(updateCommunityActivityDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.COMMUNITY_ACTIVITY_NOT_EXIST));

        CommunityActivity updateCommunityActivity = UpdateCommunityActivityDto.objToCommunityActivity(updateCommunityActivityDto);

        boolean updateResult = this.updateById(updateCommunityActivity);
        log.info("更新社区活动");

        // 发送通知
        List<ActivityRegistration> activityRegistrationList = activityRegistrationMapper.selectList(new QueryWrapper<ActivityRegistration>()
                .eq("activityId", updateCommunityActivityDtoId));

        activityRegistrationList.forEach(activityRegistration -> {
            AddMessageDto addMessageDto = new AddMessageDto();
            addMessageDto.setType(MessageType.COMMUNITY_ACTIVITY.getId());
            addMessageDto.setContent("您参与的活动信息有所调整，请及时查看");
            addMessageDto.setReceiveUser(activityRegistration.getUserId());
            messageService.addMessage(addMessageDto);
        });

        return updateResult;
    }

    @Override
    public boolean addCommunityActivity(AddCommunityActivityDto addCommunityActivityDto) {
        // 判空
        if (addCommunityActivityDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Optional.ofNullable(addCommunityActivityDto.getTitle())
                .orElseThrow(() -> new BusinessException(ErrorType.COMMUNITY_ACTIVITY_TITLE_NOT_NULL));

        Optional.ofNullable(addCommunityActivityDto.getType())
                .orElseThrow(() -> new BusinessException(ErrorType.COMMUNITY_ACTIVITY_TYPE_NOT_NULL));

        // 添加社区活动
        CommunityActivity addCommunityActivity = AddCommunityActivityDto.objToCommunityActivity(addCommunityActivityDto);
        boolean saveResult = this.save(addCommunityActivity);
        log.info("新增社区活动");

        return saveResult;
    }

    @Override
    public boolean deleteCommunityActivity(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.COMMUNITY_ACTIVITY_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除社区活动 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchCommunityActivity(Integer... communityActivityIds) {
        // 判空
        if (communityActivityIds == null || communityActivityIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<CommunityActivity> costList = this.listByIds(Arrays.asList(communityActivityIds));
        if (costList.size()!= communityActivityIds.length) {
            throw new BusinessException(ErrorType.COMMUNITY_ACTIVITY_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(communityActivityIds));
        log.info("批量删除社区活动");

        return deleteResult > 0;
    }
}




