package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.communityActivity.AddActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.QueryActivityRegistrationDto;
import com.qcx.property.domain.dto.communityActivity.UpdateActivityRegistrationDto;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.communityActivity.ActivityRegistrationVo;
import com.qcx.property.service.ActivityRegistrationService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 活动报名管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "活动报名管理")
@RestController
@RequestMapping("/activity_registration")
public class ActivityRegistrationController {

    @Resource
    private ActivityRegistrationService activityRegistrationService;

    @Operation(summary = "查询所有活动报名")
    @GetMapping
    public BaseResponse<?> getAllActivityRegistrations(QueryActivityRegistrationDto queryActivityRegistrationDto) {
        Page<ActivityRegistrationVo> costList = activityRegistrationService.getAllActivityRegistrations(queryActivityRegistrationDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部活动报名成功！");
    }

    @PutMapping
    @Operation(summary = "更新活动报名")
    public BaseResponse<?> updateActivityRegistration(UpdateActivityRegistrationDto updateActivityRegistrationDto) {
        boolean result = activityRegistrationService.updateActivityRegistration(updateActivityRegistrationDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改活动报名成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改活动报名失败！");
        }
    }

    @Operation(summary = "添加新活动报名")
    @PostMapping
    public BaseResponse<?> addActivityRegistration(AddActivityRegistrationDto addActivityRegistrationDto) {
        boolean result = activityRegistrationService.addActivityRegistration(addActivityRegistrationDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增活动报名成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增活动报名失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个活动报名")
    public BaseResponse<?> deleteActivityRegistration(@PathVariable Integer id) {
        boolean result = activityRegistrationService.deleteActivityRegistration(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除活动报名成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除活动报名失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除活动报名")
    public BaseResponse<?> deleteBatchActivityRegistration(Integer... activityRegistrationIds) {
        boolean result = activityRegistrationService.deleteBatchActivityRegistration(activityRegistrationIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除活动报名成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除活动报名失败！");
        }
    }


}
