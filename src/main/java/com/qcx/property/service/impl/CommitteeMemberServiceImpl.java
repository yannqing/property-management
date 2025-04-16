package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.committee.AddCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMemberDto;
import com.qcx.property.domain.entity.CommitteeMember;
import com.qcx.property.domain.entity.OwnersCommittee;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.vo.CommitteeMemberVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.CommitteeMemberMapper;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.CommitteeMemberService;
import com.qcx.property.service.OwnersCommitteeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 委员会成员服务实现
 * @author: yannqing
 * @create: 2025-04-16 14:00
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Service
public class CommitteeMemberServiceImpl extends ServiceImpl<CommitteeMemberMapper, CommitteeMember>
        implements CommitteeMemberService {

    @Resource
    private OwnersCommitteeService ownersCommitteeService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 分页查询委员会成员列表
     *
     * @param queryCommitteeMemberDto 查询条件
     * @return 分页数据
     */
    @Override
    public Page<CommitteeMemberVO> pageCommitteeMember(QueryCommitteeMemberDto queryCommitteeMemberDto) {
        // 参数校验
        if (queryCommitteeMemberDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 构建查询条件
        QueryWrapper<CommitteeMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(queryCommitteeMemberDto.getId() != null, "id", queryCommitteeMemberDto.getId())
                .eq(queryCommitteeMemberDto.getCommitteeId() != null, "committee_id", queryCommitteeMemberDto.getCommitteeId())
                .eq(queryCommitteeMemberDto.getUserId() != null, "user_id", queryCommitteeMemberDto.getUserId())
                .like(queryCommitteeMemberDto.getPosition() != null, "position", queryCommitteeMemberDto.getPosition())
                .ge(queryCommitteeMemberDto.getJoinDateStart() != null, "join_date", queryCommitteeMemberDto.getJoinDateStart())
                .le(queryCommitteeMemberDto.getJoinDateEnd() != null, "join_date", queryCommitteeMemberDto.getJoinDateEnd())
                .eq(queryCommitteeMemberDto.getStatus() != null, "status", queryCommitteeMemberDto.getStatus())
                .like(queryCommitteeMemberDto.getResponsibilities() != null, "responsibilities", queryCommitteeMemberDto.getResponsibilities());

        // 执行分页查询
        Page<CommitteeMember> page = this.page(
                new Page<>(queryCommitteeMemberDto.getCurrent(), queryCommitteeMemberDto.getPageSize()),
                queryWrapper
        );

        // 转换为VO返回
        Page<CommitteeMemberVO> resultPage = new Page<>();
        BeanUtils.copyProperties(page, resultPage, "records");

        List<CommitteeMemberVO> voList = new ArrayList<>();
        for (CommitteeMember record : page.getRecords()) {
            CommitteeMemberVO vo = convertToVO(record);
            voList.add(vo);
        }
        resultPage.setRecords(voList);

        log.info("分页查询委员会成员列表，当前页：{}，每页数量：{}", queryCommitteeMemberDto.getCurrent(), queryCommitteeMemberDto.getPageSize());
        return resultPage;
    }

    /**
     * 根据ID查询委员会成员详情
     *
     * @param id 成员ID
     * @return 成员详情
     */
    @Override
    public CommitteeMemberVO getCommitteeMemberById(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        CommitteeMember committeeMember = this.getById(id);
        if (committeeMember == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEMBER_NOT_EXIST);
        }

        CommitteeMemberVO vo = convertToVO(committeeMember);
        log.info("查询委员会成员详情，ID：{}", id);
        return vo;
    }

    /**
     * 根据委员会ID查询所有成员
     *
     * @param committeeId 委员会ID
     * @return 成员列表
     */
    @Override
    public List<CommitteeMemberVO> getCommitteeMembersByCommitteeId(Integer committeeId) {
        if (committeeId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查委员会是否存在
        OwnersCommittee committee = ownersCommitteeService.getById(committeeId);
        if (committee == null) {
            throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
        }

        // 查询成员列表
        LambdaQueryWrapper<CommitteeMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMember::getCommitteeId, committeeId)
                .orderByAsc(CommitteeMember::getPosition);
        List<CommitteeMember> members = this.list(queryWrapper);

        // 转换为VO返回
        List<CommitteeMemberVO> voList = members.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        log.info("查询委员会成员列表，委员会ID：{}", committeeId);
        return voList;
    }

    /**
     * 根据用户ID查询所有担任的委员会职务
     *
     * @param userId 用户ID
     * @return 成员列表
     */
    @Override
    public List<CommitteeMemberVO> getCommitteeMembersByUserId(Integer userId) {
        if (userId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 查询成员列表
        LambdaQueryWrapper<CommitteeMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMember::getUserId, userId)
                .orderByDesc(CommitteeMember::getCreateTime);
        List<CommitteeMember> members = this.list(queryWrapper);

        // 转换为VO返回
        List<CommitteeMemberVO> voList = members.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        log.info("查询用户的委员会职务列表，用户ID：{}", userId);
        return voList;
    }

    /**
     * 新增委员会成员
     *
     * @param addCommitteeMemberDto 新增成员参数
     * @return 新增结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCommitteeMember(AddCommitteeMemberDto addCommitteeMemberDto) {
        if (addCommitteeMemberDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查委员会是否存在
        OwnersCommittee committee = ownersCommitteeService.getById(addCommitteeMemberDto.getCommitteeId());
        if (committee == null) {
            throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
        }

        // 检查用户是否存在
        Integer userId = addCommitteeMemberDto.getUserId();
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        // 检查用户是否已经是该委员会成员
        LambdaQueryWrapper<CommitteeMember> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommitteeMember::getCommitteeId, addCommitteeMemberDto.getCommitteeId())
                .eq(CommitteeMember::getUserId, addCommitteeMemberDto.getUserId());
        if (this.count(queryWrapper) > 0) {
            throw new BusinessException(ErrorType.COMMITTEE_MEMBER_ALREADY_EXIST);
        }

        // DTO转实体
        CommitteeMember committeeMember = AddCommitteeMemberDto.toEntity(addCommitteeMemberDto);

        boolean result = this.save(committeeMember);
        log.info("新增委员会成员，委员会ID：{}，用户ID：{}", addCommitteeMemberDto.getCommitteeId(), addCommitteeMemberDto.getUserId());
        return result;
    }

    /**
     * 更新委员会成员
     *
     * @param updateCommitteeMemberDto 更新成员参数
     * @return 更新结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCommitteeMember(UpdateCommitteeMemberDto updateCommitteeMemberDto) {
        if (updateCommitteeMemberDto == null || updateCommitteeMemberDto.getId() == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        if (updateCommitteeMemberDto.getUserId() != null) {
            Integer userId = updateCommitteeMemberDto.getUserId();
            User user = userMapper.selectById(userId);
            if (user == null) {
                throw new BusinessException(ErrorType.USER_NOT_EXIST);
            }
        }

        // 检查成员是否存在
        CommitteeMember existingMember = this.getById(updateCommitteeMemberDto.getId());
        if (existingMember == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEMBER_NOT_EXIST);
        }

        // 如果更新了委员会ID，检查委员会是否存在
        if (updateCommitteeMemberDto.getCommitteeId() != null && 
                !updateCommitteeMemberDto.getCommitteeId().equals(existingMember.getCommitteeId())) {
            OwnersCommittee committee = ownersCommitteeService.getById(updateCommitteeMemberDto.getCommitteeId());
            if (committee == null) {
                throw new BusinessException(ErrorType.OWNERS_COMMITTEE_NOT_EXIST);
            }
        }

        // DTO转实体
        CommitteeMember committeeMember = UpdateCommitteeMemberDto.toEntity(updateCommitteeMemberDto);

        boolean result = this.updateById(committeeMember);
        log.info("更新委员会成员，ID：{}", updateCommitteeMemberDto.getId());
        return result;
    }

    /**
     * 删除委员会成员
     *
     * @param id 成员ID
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCommitteeMember(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查成员是否存在
        CommitteeMember existingMember = this.getById(id);
        if (existingMember == null) {
            throw new BusinessException(ErrorType.COMMITTEE_MEMBER_NOT_EXIST);
        }

        boolean result = this.removeById(id);
        log.info("删除委员会成员，ID：{}", id);
        return result;
    }

    /**
     * 批量删除委员会成员
     *
     * @param ids 成员ID数组
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteCommitteeMember(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查所有成员是否存在
        for (Integer id : ids) {
            CommitteeMember existingMember = this.getById(id);
            if (existingMember == null) {
                throw new BusinessException(ErrorType.COMMITTEE_MEMBER_NOT_EXIST, "成员（ID: " + id + "）不存在");
            }
        }

        boolean result = this.removeByIds(Arrays.asList(ids));
        log.info("批量删除委员会成员，ID列表：{}", Arrays.toString(ids));
        return result;
    }

    /**
     * 将实体转换为VO
     *
     * @param committeeMember 实体
     * @return VO
     */
    private CommitteeMemberVO convertToVO(CommitteeMember committeeMember) {
        if (committeeMember == null) {
            return null;
        }
        
        CommitteeMemberVO vo = new CommitteeMemberVO();
        BeanUtils.copyProperties(committeeMember, vo);
        
        // 设置状态描述
        vo.setStatusDesc(committeeMember.getStatus() != null ? 
                (committeeMember.getStatus() == 1 ? "在任" : "离任") : "");
        
        // 设置委员会名称
        if (committeeMember.getCommitteeId() != null) {
            OwnersCommittee committee = ownersCommitteeService.getById(committeeMember.getCommitteeId());
            if (committee != null) {
                vo.setCommitteeName(committee.getName());
            }
        }
        
        // TODO: 用户相关信息需要查询用户服务获取
        
        return vo;
    }
} 