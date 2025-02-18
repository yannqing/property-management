package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.takeoutsRecord.TakeoutsVo;
import com.qcx.property.service.TakeoutPickupRecordService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 外卖代收管理
 * @author: yannqing
 * @create: 2025-02-17 09:43
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "外卖代收管理")
@RestController
@RequestMapping("/takeouts")
public class TakeoutsController {

    @Resource
    private TakeoutPickupRecordService takeoutPickupRecordService;

    @Operation(summary = "查询所有外卖记录")
    @GetMapping
    public BaseResponse<?> getAllTakeouts(QueryTakeoutsDto queryTakeoutsDto) {
        Page<TakeoutsVo> costList = takeoutPickupRecordService.getAllTakeouts(queryTakeoutsDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部外卖记录成功！");
    }

    @PutMapping("/admin")
    @Operation(summary = "更新外卖记录（管理员）")
    public BaseResponse<?> updateTakeouts(UpdateTakeoutsDto updateTakeoutsDto) {
        boolean result = takeoutPickupRecordService.updateTakeouts(updateTakeoutsDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改外卖记录失败！");
        }
    }

    @PutMapping
    @Operation(summary = "更新外卖记录（用户）")
    public BaseResponse<?> updateTakeoutsByUser(UpdateTakeoutsByUserDto updateTakeoutsDto) {
        boolean result = takeoutPickupRecordService.updateTakeoutsByUser(updateTakeoutsDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改外卖记录失败！");
        }
    }

    @Operation(summary = "添加新外卖记录")
    @PostMapping
    public BaseResponse<?> addTakeouts(AddTakeoutsDto addTakeoutsDto) {
        boolean result = takeoutPickupRecordService.addTakeouts(addTakeoutsDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增外卖记录失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "外卖订单 id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个外卖记录")
    public BaseResponse<?> deleteTakeouts(@PathVariable Integer id) {
        boolean result = takeoutPickupRecordService.deleteTakeouts(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除外卖记录失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "takeoutIds", description = "外卖订单 id 数组", required = true))
    @Operation(summary = "批量删除外卖记录")
    public BaseResponse<?> deleteBatchTakeouts(Integer... takeoutIds) {
        boolean result = takeoutPickupRecordService.deleteBatchTakeouts(takeoutIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除外卖记录失败！");
        }
    }

    @Operation(summary = "骑手接单")
    @Parameters({
            @Parameter(name = "userId", description = "骑手 id", required = true),
            @Parameter(name = "takeoutsId", description = "外卖订单 id", required = true)
    })
    @PostMapping("/accepts")
    public BaseResponse<?> acceptOrder(Integer userId, Integer takeoutsId) {
        boolean result = takeoutPickupRecordService.acceptOrder(userId, takeoutsId);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "骑手接单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "骑手接单失败！");
        }
    }

    @Operation(summary = "开始派送")
    @Parameters(@Parameter(name = "takeoutsId", description = "外卖订单 id", required = true))
    @PostMapping("/delivery_order")
    public BaseResponse<?> deliverOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException {
        boolean result = takeoutPickupRecordService.deliverOrder(takeoutsId, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "开始派送成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "开始派送失败！");
        }
    }

    @Operation(summary = "订单送达")
    @Parameters({
            @Parameter(name = "takeoutsId", description = "外卖订单 id", required = true),
            @Parameter(name = "cabinetId", description = "外卖柜 id", required = true)
    })
    @PostMapping("/arrive")
    public BaseResponse<?> takeoutsArrive(Integer takeoutsId, Integer cabinetId, HttpServletRequest request) throws JsonProcessingException {
        boolean result = takeoutPickupRecordService.takeoutsArrive(takeoutsId, cabinetId, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "外卖送达成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "外卖送达失败失败！");
        }
    }

    @Operation(summary = "用户领取订单")
    @Parameters({
            @Parameter(name = "takeoutsId", description = "外卖订单 id", required = true)
    })
    @PostMapping("/receive_order")
    public BaseResponse<?> receiveOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException {
        boolean result = takeoutPickupRecordService.receiveOrder(takeoutsId, request);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "外卖送达成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "外卖送达失败！");
        }
    }
}
