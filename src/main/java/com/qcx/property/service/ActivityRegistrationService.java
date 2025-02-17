package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.communityActivity.AddActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.QueryActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.UpdateActivityRegistrationDto;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.communityActivity.ActivityRegistrationVo;

/**
* @author 67121
* @description 针对表【activity_registration(活动报名表)】的数据库操作Service
* @createDate 2025-02-14 17:09:54
*/
public interface ActivityRegistrationService extends IService<ActivityRegistration> {

    /**
     * 查询所有活动报名
     * @param queryActivityRegistrationDto 查询 dto
     * @return 查询结果
     */
    Page<ActivityRegistrationVo> getAllActivityRegistrations(QueryActivityRegistrationDto queryActivityRegistrationDto);

    /**
     * 更新活动报名
     * @param updateActivityRegistrationDto 更新 dto
     * @return 更新结果
     */
    boolean updateActivityRegistration(UpdateActivityRegistrationDto updateActivityRegistrationDto);

    /**
     * 新增活动报名
     * @param addActivityRegistrationDto 新增 dto
     * @return 返回新增结果
     */
    boolean addActivityRegistration(AddActivityRegistrationDto addActivityRegistrationDto);

    /**
     * 删除单个活动报名
     * @param id 活动报名 id
     * @return 返回删除结果
     */
    boolean deleteActivityRegistration(Integer id);

    /**
     * 批量删除活动报名
     * @param activityRegistrationIds 活动报名 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchActivityRegistration(Integer[] activityRegistrationIds);
}
