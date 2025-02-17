package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.communityActivity.AddCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.QueryCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.UpdateCommunityActivityDto;
import com.qcx.property.domain.entity.CommunityActivity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.communityActivity.CommunityActivityVo;

/**
* @author 67121
* @description 针对表【community_activity(社区活动表)】的数据库操作Service
* @createDate 2025-02-14 17:09:46
*/
public interface CommunityActivityService extends IService<CommunityActivity> {

    /**
     * 查询所有社区活动
     * @param queryCommunityActivityDto 查询 dto
     * @return 查询结果
     */
    Page<CommunityActivityVo> getAllCommunityActivities(QueryCommunityActivityDto queryCommunityActivityDto);

    /**
     * 更新社区活动
     * @param updateCommunityActivityDto 更新 dto
     * @return 更新结果
     */
    boolean updateCommunityActivity(UpdateCommunityActivityDto updateCommunityActivityDto);

    /**
     * 新增社区活动
     * @param addCommunityActivityDto 新增 dto
     * @return 新增结果
     */
    boolean addCommunityActivity(AddCommunityActivityDto addCommunityActivityDto);

    /**
     * 删除单个社区活动
     * @param id 社区活动 id
     * @return 删除结果
     */
    boolean deleteCommunityActivity(Integer id);

    /**
     * 批量删除社区活动
     * @param communityActivityIds 社区活动 id 数组
     * @return 删除结果
     */
    boolean deleteBatchCommunityActivity(Integer... communityActivityIds);
}
