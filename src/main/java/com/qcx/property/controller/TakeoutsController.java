package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.TakeoutPickupRecordService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
        Page<TakeoutPickupRecord> costList = takeoutPickupRecordService.getAllTakeouts(queryTakeoutsDto);
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
    @Operation(summary = "批量删除外卖记录")
    public BaseResponse<?> deleteBatchTakeouts(Integer... takeoutIds) {
        boolean result = takeoutPickupRecordService.deleteBatchTakeouts(takeoutIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除外卖记录成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除外卖记录失败！");
        }
    }


}
