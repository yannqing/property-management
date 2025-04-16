package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.dto.committee.AddCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMemberDto;
import com.qcx.property.domain.entity.CommitteeMember;
import com.qcx.property.domain.vo.CommitteeMemberVO;

import java.util.List;

/**
 * @description: 委员会成员服务接口
 * @author: yannqing
 * @create: 2025-04-16 13:10
 * @from: <更多资料：yannqing.com>
 **/
public interface CommitteeMemberService extends IService<CommitteeMember> {

    /**
     * 分页查询委员会成员列表
     * @param queryCommitteeMemberDto 查询条件
     * @return 分页数据
     */
    Page<CommitteeMemberVO> pageCommitteeMember(QueryCommitteeMemberDto queryCommitteeMemberDto);

    /**
     * 根据ID查询委员会成员详情
     * @param id 成员ID
     * @return 成员详情
     */
    CommitteeMemberVO getCommitteeMemberById(Integer id);

    /**
     * 根据委员会ID查询所有成员
     * @param committeeId 委员会ID
     * @return 成员列表
     */
    List<CommitteeMemberVO> getCommitteeMembersByCommitteeId(Integer committeeId);

    /**
     * 根据用户ID查询所有担任的委员会职务
     * @param userId 用户ID
     * @return 成员列表
     */
    List<CommitteeMemberVO> getCommitteeMembersByUserId(Integer userId);

    /**
     * 新增委员会成员
     * @param addCommitteeMemberDto 新增成员参数
     * @return 新增结果
     */
    boolean addCommitteeMember(AddCommitteeMemberDto addCommitteeMemberDto);

    /**
     * 更新委员会成员
     * @param updateCommitteeMemberDto 更新成员参数
     * @return 更新结果
     */
    boolean updateCommitteeMember(UpdateCommitteeMemberDto updateCommitteeMemberDto);

    /**
     * 删除委员会成员
     * @param id 成员ID
     * @return 删除结果
     */
    boolean deleteCommitteeMember(Integer id);
    
    /**
     * 批量删除委员会成员
     * @param ids 成员ID数组
     * @return 删除结果
     */
    boolean batchDeleteCommitteeMember(Integer[] ids);
} 