package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.dto.committee.AddCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMeetingDto;
import com.qcx.property.domain.entity.CommitteeMeeting;
import com.qcx.property.domain.vo.CommitteeMeetingVO;

import java.util.List;

/**
 * @description: 委员会会议服务接口
 * @author: yannqing
 * @create: 2025-04-16 13:20
 * @from: <更多资料：yannqing.com>
 **/
public interface CommitteeMeetingService extends IService<CommitteeMeeting> {

    /**
     * 分页查询委员会会议列表
     * @param queryCommitteeMeetingDto 查询条件
     * @return 分页数据
     */
    Page<CommitteeMeetingVO> pageCommitteeMeeting(QueryCommitteeMeetingDto queryCommitteeMeetingDto);

    /**
     * 根据ID查询委员会会议详情
     * @param id 会议ID
     * @return 会议详情
     */
    CommitteeMeetingVO getCommitteeMeetingById(Integer id);

    /**
     * 根据委员会ID查询所有会议
     * @param committeeId 委员会ID
     * @return 会议列表
     */
    List<CommitteeMeetingVO> getCommitteeMeetingsByCommitteeId(Integer committeeId);

    /**
     * 根据组织者ID查询所有会议
     * @param organizerId 组织者ID
     * @return 会议列表
     */
    List<CommitteeMeetingVO> getCommitteeMeetingsByOrganizerId(Integer organizerId);

    /**
     * 统计委员会会议数量
     * @param committeeId 委员会ID
     * @return 会议数量
     */
    int countCommitteeMeetingsByCommitteeId(Integer committeeId);

    /**
     * 新增委员会会议
     * @param addCommitteeMeetingDto 新增会议参数
     * @return 新增结果
     */
    boolean addCommitteeMeeting(AddCommitteeMeetingDto addCommitteeMeetingDto);

    /**
     * 更新委员会会议
     * @param updateCommitteeMeetingDto 更新会议参数
     * @return 更新结果
     */
    boolean updateCommitteeMeeting(UpdateCommitteeMeetingDto updateCommitteeMeetingDto);

    /**
     * 删除委员会会议
     * @param id 会议ID
     * @return 删除结果
     */
    boolean deleteCommitteeMeeting(Integer id);
    
    /**
     * 批量删除委员会会议
     * @param ids 会议ID数组
     * @return 删除结果
     */
    boolean batchDeleteCommitteeMeeting(Integer[] ids);
}