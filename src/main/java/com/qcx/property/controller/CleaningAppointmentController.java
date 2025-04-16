package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.cleaning.AddCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningAppointmentDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.CleaningAppointmentVO;
import com.qcx.property.service.CleaningAppointmentService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 保洁预约管理
 * @author: yannqing
 * @create: 2025-02-09 11:45
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "保洁预约管理")
@RestController
@RequestMapping("/cleaning/appointment")
public class CleaningAppointmentController {

    @Resource
    private CleaningAppointmentService cleaningAppointmentService;

    @Operation(summary = "查询保洁预约")
    @GetMapping
    public BaseResponse<?> getCleaningAppointments(QueryCleaningAppointmentDto queryCleaningAppointmentDto) {
        Page<CleaningAppointmentVO> page = cleaningAppointmentService.getCleaningAppointments(queryCleaningAppointmentDto);
        return ResultUtils.success(Code.SUCCESS, page, "查询保洁预约成功！");
    }

    @Operation(summary = "根据id查询保洁预约")
    @GetMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> getCleaningAppointmentById(@PathVariable Integer id) {
        CleaningAppointmentVO appointmentVO = cleaningAppointmentService.getCleaningAppointmentById(id);
        return ResultUtils.success(Code.SUCCESS, appointmentVO, "查询保洁预约成功！");
    }

    @Operation(summary = "查询用户的保洁预约列表")
    @GetMapping("/user/{userId}")
    @Parameters(@Parameter(name = "userId", description = "用户id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> getCleaningAppointmentsByUserId(@PathVariable Integer userId) {
        List<CleaningAppointmentVO> list = cleaningAppointmentService.getCleaningAppointmentsByUserId(userId);
        return ResultUtils.success(Code.SUCCESS, list, "查询用户保洁预约成功！");
    }

    @Operation(summary = "添加保洁预约")
    @PostMapping
    public BaseResponse<?> addCleaningAppointment(@RequestBody AddCleaningAppointmentDto addCleaningAppointmentDto) {
        boolean result = cleaningAppointmentService.addCleaningAppointment(addCleaningAppointmentDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "添加保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "添加保洁预约失败！");
        }
    }

    @Operation(summary = "更新保洁预约")
    @PutMapping
    public BaseResponse<?> updateCleaningAppointment(@RequestBody UpdateCleaningAppointmentDto updateCleaningAppointmentDto) {
        boolean result = cleaningAppointmentService.updateCleaningAppointment(updateCleaningAppointmentDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "更新保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "更新保洁预约失败！");
        }
    }

    @Operation(summary = "用户取消保洁预约")
    @PostMapping("/cancel/{id}")
    @Parameters({
            @Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH),
            @Parameter(name = "userId", description = "用户id", required = true)
    })
    public BaseResponse<?> cancelCleaningAppointment(@PathVariable Integer id, @RequestParam Integer userId) {
        boolean result = cleaningAppointmentService.cancelCleaningAppointment(id, userId);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "取消保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "取消保洁预约失败！");
        }
    }

    @Operation(summary = "管理员取消保洁预约")
    @PostMapping("/admin/cancel/{id}")
    @Parameters(@Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> cancelCleaningAppointmentByAdmin(@PathVariable Integer id) {
        boolean result = cleaningAppointmentService.cancelCleaningAppointmentByAdmin(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "管理员取消保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "管理员取消保洁预约失败！");
        }
    }

    @Operation(summary = "管理员确认保洁预约")
    @PostMapping("/admin/confirm/{id}")
    @Parameters(@Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> confirmCleaningAppointment(@PathVariable Integer id) {
        boolean result = cleaningAppointmentService.confirmCleaningAppointment(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "确认保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "确认保洁预约失败！");
        }
    }

    @Operation(summary = "管理员拒绝保洁预约")
    @PostMapping("/admin/reject/{id}")
    @Parameters(@Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> rejectCleaningAppointment(@PathVariable Integer id) {
        boolean result = cleaningAppointmentService.rejectCleaningAppointment(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "拒绝保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "拒绝保洁预约失败！");
        }
    }

    @Operation(summary = "管理员完成保洁预约")
    @PostMapping("/admin/complete/{id}")
    @Parameters(@Parameter(name = "id", description = "预约id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> completeCleaningAppointment(@PathVariable Integer id) {
        boolean result = cleaningAppointmentService.completeCleaningAppointment(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "完成保洁预约成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "完成保洁预约失败！");
        }
    }
} 