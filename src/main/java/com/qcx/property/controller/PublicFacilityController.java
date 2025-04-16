package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.facility.AddPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.QueryPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.UpdatePublicFacilityDTO;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.PublicFacilityVO;
import com.qcx.property.service.PublicFacilityService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 公共设施管理
 * @author: yannqing
 * @create: 2025-02-25 15:30
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "公共设施管理")
@RestController
@RequestMapping("/public_facility")
public class PublicFacilityController {

    @Resource
    private PublicFacilityService publicFacilityService;

    @Operation(summary = "查询所有公共设施")
    @GetMapping
    public BaseResponse<?> getAllPublicFacilities(QueryPublicFacilityDTO queryPublicFacilityDTO) {
        Page<PublicFacilityVO> facilityList = publicFacilityService.getAllPublicFacilities(queryPublicFacilityDTO);
        return ResultUtils.success(Code.SUCCESS, facilityList, "查询全部公共设施成功！");
    }

    @Operation(summary = "获取公共设施详情")
    @GetMapping("/{id}")
    public BaseResponse<?> getPublicFacilityDetail(@PathVariable Integer id) {
        PublicFacilityVO facility = publicFacilityService.getPublicFacilityDetail(id);
        return ResultUtils.success(Code.SUCCESS, facility, "查询公共设施详情成功！");
    }

    @PutMapping
    @Operation(summary = "更新公共设施")
    public BaseResponse<?> updatePublicFacility(@RequestBody UpdatePublicFacilityDTO updatePublicFacilityDTO) {
        boolean result = publicFacilityService.updatePublicFacility(updatePublicFacilityDTO);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改公共设施成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改公共设施失败！");
        }
    }

    @Operation(summary = "添加新公共设施")
    @PostMapping
    public BaseResponse<?> addPublicFacility(@RequestBody AddPublicFacilityDTO addPublicFacilityDTO) {
        boolean result = publicFacilityService.addPublicFacility(addPublicFacilityDTO);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增公共设施成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增公共设施失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "公共设施id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个公共设施")
    public BaseResponse<?> deletePublicFacility(@PathVariable Integer id) {
        boolean result = publicFacilityService.deletePublicFacility(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除公共设施成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除公共设施失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "facilityIds", description = "公共设施id数组", required = true))
    @Operation(summary = "批量删除公共设施")
    public BaseResponse<?> batchDeletePublicFacility(@RequestParam Integer[] facilityIds) {
        boolean result = publicFacilityService.batchDeletePublicFacility(facilityIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除公共设施成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除公共设施失败！");
        }
    }
} 