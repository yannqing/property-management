package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.committee.AddCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMeetingDto;
import com.qcx.property.domain.entity.CommitteeMeeting;
import com.qcx.property.domain.entity.OwnersCommittee;
import com.qcx.property.domain.vo.CommitteeMeetingVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.CommitteeMeetingMapper;
import com.qcx.property.service.CommitteeMeetingService;
import com.qcx.property.service.OwnersCommitteeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 委员会会议服务实现
 * @author: yannqing
 * @create: 2025-04-16 14:30
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Service
public class CommitteeMeetingServiceImpl extends ServiceImpl<CommitteeMeetingMapper, CommitteeMeeting>
        implements CommitteeMeetingService {

    @Resource
    private OwnersCommitteeService ownersCommitteeService;

    /**
     * 分页查询委员会会议列表
     *
     * @param queryCommitteeMeetingDto 查询条件
     * @return 分页数据
     */
    @Override
    public Page<CommitteeMeetingVO> pageCommitteeMeeting(QueryCommitteeMeetingDto queryCommitteeMeetingDto) {
        // 参数校验
        if (queryCommitteeMeetingDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 构建查询条件
        QueryWrapper<CommitteeMeeting> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(queryCommitteeMeetingDto.getId() != null, "id", queryCommitteeMeetingDto.getId())
                .eq(queryCommitteeMeetingDto.getCommitteeId() != null, "committee_id", queryCommitteeMeetingDto.getCommitteeId())
                .like(queryCommitteeMeetingDto.getTitle() != null, "title", queryCommitteeMeetingDto.getTitle())
                .ge(queryCommitteeMeetingDto.getMeetingDateStart() != null, "meeting_date", queryCommitteeMeetingDto.getMeetingDateStart())
                .le(queryCommitteeMeetingDto.getMeetingDateEnd() != null, "meeting_date", queryCommitteeMeetingDto.getMeetingDateEnd())
                .like(queryCommitteeMeetingDto.getLocation() != null, "location", queryCommitteeMeetingDto.getLocation())
                .eq(queryCommitteeMeetingDto.getOrganizerId() != null, "organizer_id", queryCommitteeMeetingDto.getOrganizerId())
                .eq(queryCommitteeMeetingDto.getStatus() != null, "status", queryCommitteeMeetingDto.getStatus());

        // 执行分页查询
        Page<CommitteeMeeting> page = this.page(
                new Page<>(queryCommitteeMeetingDto.getCurrent(), queryCommitteeMeetingDto.getPageSize()),
                queryWrapper
        );

        // 转换为VO返回
        Page<CommitteeMeetingVO> resultPage = new Page<>();
        BeanUtils.copyProperties(page, resultPage, "records");

        List<CommitteeMeetingVO> voList = new ArrayList<>();
        for (CommitteeMeeting record : page.getRecords()) {
            CommitteeMeetingVO vo = convertToVO(record);
            voList.add(vo);
        }
        resultPage.setRecords(voList);

        log.info("分页查询委员会会议列表，当前页：{}，每页数量：{}", queryCommitteeMeetingDto.getCurrent(), queryCommitteeMeetingDto.getPageSize());
        return resultPage;
    }

    /**
     * 根据ID查询委员会会议详情
     *
     * @param id 会议ID
     * @return 会议详情
     */
    @Override
    public CommitteeMeetingVO getCommitteeMeetingById(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        CommitteeMeeting committeeMeeting = this.getById(id);
        if (committeeMeeting == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_NOT_EXIST);
        }

        CommitteeMeetingVO vo = convertToVO(committeeMeeting);
        log.info("查询委员会会议详情，ID：{}", id);
        return vo;
    }

    /**
     * 根据委员会ID查询所有会议
     *
     * @param committeeId 委员会ID
     * @return 会议列表
     */
    @Override
    public List<CommitteeMeetingVO> getCommitteeMeetingsByCommitteeId(Integer committeeId) {
        if (committeeId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查委员会是否存在
        OwnersCommittee committee = ownersCommitteeService.getById(committeeId);
        if (committee == null) {
            throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
        }

        // 查询会议列表
        LambdaQueryWrapper<CommitteeMeeting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMeeting::getCommitteeId, committeeId)
                .orderByDesc(CommitteeMeeting::getMeetingDate);
        List<CommitteeMeeting> meetings = this.list(queryWrapper);

        // 转换为VO返回
        List<CommitteeMeetingVO> voList = meetings.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        log.info("查询委员会会议列表，委员会ID：{}", committeeId);
        return voList;
    }

    /**
     * 根据组织者ID查询所有会议
     *
     * @param organizerId 组织者ID
     * @return 会议列表
     */
    @Override
    public List<CommitteeMeetingVO> getCommitteeMeetingsByOrganizerId(Integer organizerId) {
        if (organizerId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 查询会议列表
        LambdaQueryWrapper<CommitteeMeeting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMeeting::getOrganizerId, organizerId)
                .orderByDesc(CommitteeMeeting::getMeetingDate);
        List<CommitteeMeeting> meetings = this.list(queryWrapper);

        // 转换为VO返回
        List<CommitteeMeetingVO> voList = meetings.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        log.info("查询组织者的会议列表，组织者ID：{}", organizerId);
        return voList;
    }

    /**
     * 统计委员会会议数量
     *
     * @param committeeId 委员会ID
     * @return 会议数量
     */
    @Override
    public int countCommitteeMeetingsByCommitteeId(Integer committeeId) {
        if (committeeId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        LambdaQueryWrapper<CommitteeMeeting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMeeting::getCommitteeId, committeeId);
        
        return Math.toIntExact(this.count(queryWrapper));
    }

    /**
     * 新增委员会会议
     *
     * @param addCommitteeMeetingDto 新增会议参数
     * @return 新增结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCommitteeMeeting(AddCommitteeMeetingDto addCommitteeMeetingDto) {
        if (addCommitteeMeetingDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查委员会是否存在
        OwnersCommittee committee = ownersCommitteeService.getById(addCommitteeMeetingDto.getCommitteeId());
        if (committee == null) {
            throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
        }

        // 验证会议标题不为空
        if (addCommitteeMeetingDto.getTitle() == null || addCommitteeMeetingDto.getTitle().trim().isEmpty()) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_TITLE_NOT_NULL);
        }

        // 检查同一时间是否有其他会议
        checkMeetingTimeConflict(addCommitteeMeetingDto.getCommitteeId(), null, addCommitteeMeetingDto.getMeetingDate());

        // DTO转实体
        CommitteeMeeting committeeMeeting = AddCommitteeMeetingDto.toEntity(addCommitteeMeetingDto);

        boolean result = this.save(committeeMeeting);
        log.info("新增委员会会议，委员会ID：{}，会议标题：{}", addCommitteeMeetingDto.getCommitteeId(), addCommitteeMeetingDto.getTitle());
        return result;
    }

    /**
     * 更新委员会会议
     *
     * @param updateCommitteeMeetingDto 更新会议参数
     * @return 更新结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCommitteeMeeting(UpdateCommitteeMeetingDto updateCommitteeMeetingDto) {
        if (updateCommitteeMeetingDto == null || updateCommitteeMeetingDto.getId() == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查会议是否存在
        CommitteeMeeting existingMeeting = this.getById(updateCommitteeMeetingDto.getId());
        if (existingMeeting == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_NOT_EXIST);
        }

        // 如果更新了委员会ID，检查委员会是否存在
        if (updateCommitteeMeetingDto.getCommitteeId() != null && 
                !updateCommitteeMeetingDto.getCommitteeId().equals(existingMeeting.getCommitteeId())) {
            OwnersCommittee committee = ownersCommitteeService.getById(updateCommitteeMeetingDto.getCommitteeId());
            if (committee == null) {
                throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
            }
        }

        // 如果更新了状态为"已召开"或"已取消"，检查原状态是否为"筹备中"
        if (updateCommitteeMeetingDto.getStatus() != null && 
                (updateCommitteeMeetingDto.getStatus() == 1 || updateCommitteeMeetingDto.getStatus() == 2) &&
                existingMeeting.getStatus() != 0) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_CANNOT_CANCEL, "会议状态不是筹备中，无法更改状态");
        }

        // 如果更新了会议时间，检查时间冲突
        if (updateCommitteeMeetingDto.getMeetingDate() != null && 
                !updateCommitteeMeetingDto.getMeetingDate().equals(existingMeeting.getMeetingDate())) {
            Integer committeeId = updateCommitteeMeetingDto.getCommitteeId() != null ? 
                    updateCommitteeMeetingDto.getCommitteeId() : existingMeeting.getCommitteeId();
            checkMeetingTimeConflict(committeeId, updateCommitteeMeetingDto.getId(), updateCommitteeMeetingDto.getMeetingDate());
        }

        // DTO转实体
        CommitteeMeeting committeeMeeting = UpdateCommitteeMeetingDto.toEntity(updateCommitteeMeetingDto);

        boolean result = this.updateById(committeeMeeting);
        log.info("更新委员会会议，ID：{}", updateCommitteeMeetingDto.getId());
        return result;
    }

    /**
     * 删除委员会会议
     *
     * @param id 会议ID
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCommitteeMeeting(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查会议是否存在
        CommitteeMeeting existingMeeting = this.getById(id);
        if (existingMeeting == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_NOT_EXIST);
        }

        // 检查会议状态，已召开的会议不允许删除
        if (existingMeeting.getStatus() == 1) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_CANNOT_CANCEL, "已召开的会议不允许删除");
        }

        boolean result = this.removeById(id);
        log.info("删除委员会会议，ID：{}", id);
        return result;
    }

    /**
     * 批量删除委员会会议
     *
     * @param ids 会议ID数组
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteCommitteeMeeting(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查所有会议是否存在，以及状态是否允许删除
        for (Integer id : ids) {
            CommitteeMeeting existingMeeting = this.getById(id);
            if (existingMeeting == null) {
                throw new BusinessException(ErrorType.COMMITTEE_MEETING_NOT_EXIST, "会议（ID: " + id + "）不存在");
            }
            
            // 检查会议状态，已召开的会议不允许删除
            if (existingMeeting.getStatus() == 1) {
                throw new BusinessException(ErrorType.COMMITTEE_MEETING_CANNOT_CANCEL, "已召开的会议（ID: " + id + "）不允许删除");
            }
        }

        boolean result = this.removeByIds(Arrays.asList(ids));
        log.info("批量删除委员会会议，ID列表：{}", Arrays.toString(ids));
        return result;
    }

    /**
     * 检查会议时间冲突
     *
     * @param committeeId 委员会ID
     * @param meetingId 排除的会议ID（更新时使用）
     * @param meetingDate 会议时间
     */
    private void checkMeetingTimeConflict(Integer committeeId, Integer meetingId, Date meetingDate) {
        if (committeeId == null || meetingDate == null) {
            return;
        }

        // 查询同一委员会在同一天的会议
        LambdaQueryWrapper<CommitteeMeeting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMeeting::getCommitteeId, committeeId)
                // 查询当天会议（使用日期比较）
                .apply("DATE_FORMAT(meeting_date, '%Y-%m-%d') = DATE_FORMAT({0}, '%Y-%m-%d')", meetingDate);
        
        // 如果是更新操作，排除当前会议
        if (meetingId != null) {
            queryWrapper.ne(CommitteeMeeting::getId, meetingId);
        }
        
        List<CommitteeMeeting> meetings = this.list(queryWrapper);
        if (!meetings.isEmpty()) {
            throw new BusinessException(ErrorType.COMMITTEE_MEETING_CONFLICT);
        }
    }

    /**
     * 将实体转换为VO
     *
     * @param committeeMeeting 实体
     * @return VO
     */
    private CommitteeMeetingVO convertToVO(CommitteeMeeting committeeMeeting) {
        if (committeeMeeting == null) {
            return null;
        }
        
        CommitteeMeetingVO vo = new CommitteeMeetingVO();
        BeanUtils.copyProperties(committeeMeeting, vo);
        
        // 设置状态描述
        if (committeeMeeting.getStatus() != null) {
            switch (committeeMeeting.getStatus()) {
                case 0:
                    vo.setStatusDesc("筹备中");
                    break;
                case 1:
                    vo.setStatusDesc("已召开");
                    break;
                case 2:
                    vo.setStatusDesc("已取消");
                    break;
                default:
                    vo.setStatusDesc("未知状态");
            }
        }
        
        // 设置委员会名称
        if (committeeMeeting.getCommitteeId() != null) {
            OwnersCommittee committee = ownersCommitteeService.getById(committeeMeeting.getCommitteeId());
            if (committee != null) {
                vo.setCommitteeName(committee.getName());
            }
        }
        
        // TODO: 组织者相关信息需要查询用户服务获取
        
        return vo;
    }
} 