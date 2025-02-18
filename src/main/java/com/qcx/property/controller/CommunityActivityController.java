package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.communityActivity.AddCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.QueryCommunityActivityDto;
import com.qcx.property.domain.dto.communityActivity.UpdateCommunityActivityDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.communityActivity.CommunityActivityVo;
import com.qcx.property.service.CommunityActivityService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 社区活动管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "社区活动管理")
@RestController
@RequestMapping("/community_activity")
public class CommunityActivityController {

    @Resource
    private CommunityActivityService communityActivityService;

    @Operation(summary = "查询所有社区活动")
    @GetMapping
    public BaseResponse<?> getAllCommunityActivities(QueryCommunityActivityDto queryCommunityActivityDto) {
        Page<CommunityActivityVo> costList = communityActivityService.getAllCommunityActivities(queryCommunityActivityDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部社区活动成功！");
    }

    @PutMapping
    @Operation(summary = "更新社区活动")
    public BaseResponse<?> updateCommunityActivity(UpdateCommunityActivityDto updateCommunityActivityDto) {
        boolean result = communityActivityService.updateCommunityActivity(updateCommunityActivityDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改社区活动成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改社区活动失败！");
        }
    }

    @Operation(summary = "添加新社区活动")
    @PostMapping
    public BaseResponse<?> addCommunityActivity(AddCommunityActivityDto addCommunityActivityDto) {
        boolean result = communityActivityService.addCommunityActivity(addCommunityActivityDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增社区活动成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增社区活动失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "社区活动 id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个社区活动")
    public BaseResponse<?> deleteCommunityActivity(@PathVariable Integer id) {
        boolean result = communityActivityService.deleteCommunityActivity(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除社区活动成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除社区活动失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "communityActivityIds", description = "社区活动 id 数组", required = true))
    @Operation(summary = "批量删除社区活动")
    public BaseResponse<?> deleteBatchCommunityActivity(Integer... communityActivityIds) {
        boolean result = communityActivityService.deleteBatchCommunityActivity(communityActivityIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除社区活动成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除社区活动失败！");
        }
    }


}
