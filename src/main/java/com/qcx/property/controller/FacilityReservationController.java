package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.reservation.AddFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.QueryFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.UpdateFacilityReservationDTO;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.reservation.FacilityReservationVO;
import com.qcx.property.service.FacilityReservationService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 设施预约管理
 * @author: yannqing
 * @create: 2025-02-25 15:35
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "设施预约管理")
@RestController
@RequestMapping("/facility_reservation")
public class FacilityReservationController {

    @Resource
    private FacilityReservationService facilityReservationService;

    @Operation(summary = "查询设施预约列表")
    @GetMapping
    public BaseResponse<?> getAllFacilityReservations(@ModelAttribute QueryFacilityReservationDTO queryFacilityReservationDTO) {
        Page<FacilityReservationVO> reservationList = facilityReservationService.getAllFacilityReservations(queryFacilityReservationDTO);
        return ResultUtils.success(Code.SUCCESS, reservationList, "查询设施预约列表成功！");
    }

    @Operation(summary = "获取设施预约详情")
    @GetMapping("/{id}")
    public BaseResponse<?> getFacilityReservationDetail(@PathVariable Integer id) {
        FacilityReservationVO reservation = facilityReservationService.getFacilityReservationDetail(id);
        return ResultUtils.success(Code.SUCCESS, reservation, "查询设施预约详情成功！");
    }

    @PutMapping
    @Operation(summary = "更新设施预约")
    public BaseResponse<?> updateFacilityReservation(@RequestBody UpdateFacilityReservationDTO updateFacilityReservationDTO) {
        boolean result = facilityReservationService.updateFacilityReservation(updateFacilityReservationDTO);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改设施预约失败！");
        }
    }

    @Operation(summary = "添加设施预约", description = "必须提供用户ID(userId)、设施ID(facilityId)、预约时间段和使用人数")
    @PostMapping
    public BaseResponse<?> addFacilityReservation(@RequestBody AddFacilityReservationDTO addFacilityReservationDTO) {
        boolean result = facilityReservationService.addFacilityReservation(addFacilityReservationDTO);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增设施预约失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "设施预约id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除设施预约")
    public BaseResponse<?> deleteFacilityReservation(@PathVariable Integer id) {
        boolean result = facilityReservationService.deleteFacilityReservation(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除设施预约失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "reservationIds", description = "设施预约id数组", required = true))
    @Operation(summary = "批量删除设施预约")
    public BaseResponse<?> batchDeleteFacilityReservation(@RequestParam Integer[] reservationIds) {
        boolean result = facilityReservationService.batchDeleteFacilityReservation(reservationIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除设施预约失败！");
        }
    }
    
    @PutMapping("/cancel/{id}")
    @Parameters(@Parameter(name = "id", description = "设施预约id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "取消设施预约")
    public BaseResponse<?> cancelFacilityReservation(@PathVariable Integer id) {
        boolean result = facilityReservationService.cancelFacilityReservation(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "取消设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "取消设施预约失败！");
        }
    }
    
    @PutMapping("/audit/{id}")
    @Parameters({
        @Parameter(name = "id", description = "设施预约id", required = true, in = ParameterIn.PATH),
        @Parameter(name = "status", description = "审核状态(1已通过,2已拒绝)", required = true),
        @Parameter(name = "remarks", description = "备注")
    })
    @Operation(summary = "审核设施预约")
    public BaseResponse<?> auditFacilityReservation(
            @PathVariable Integer id,
            @RequestParam Integer status,
            @RequestParam(required = false) String remarks) {
        boolean result = facilityReservationService.auditFacilityReservation(id, status, remarks);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "审核设施预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "审核设施预约失败！");
        }
    }
} 