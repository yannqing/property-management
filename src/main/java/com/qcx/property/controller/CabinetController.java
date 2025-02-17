package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.takeouts.AddCabinetDto;
import com.qcx.property.domain.dto.takeouts.QueryCabinetDto;
import com.qcx.property.domain.dto.takeouts.UpdateCabinetDto;
import com.qcx.property.domain.entity.Cabinet;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.CabinetService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 外卖柜管理
 * @author: yannqing
 * @create: 2025-02-17 1219
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "外卖柜管理")
@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    @Resource
    private CabinetService cabinetService;

    @Operation(summary = "查询所有外卖柜")
    @GetMapping
    public BaseResponse<?> getAllCabinets(QueryCabinetDto queryCabinetDto) {
        Page<Cabinet> costList = cabinetService.getAllCabinets(queryCabinetDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部外卖柜成功！");
    }

    @PutMapping
    @Operation(summary = "更新外卖柜")
    public BaseResponse<?> updateCabinet(UpdateCabinetDto updateCabinetDto) {
        boolean result = cabinetService.updateCabinet(updateCabinetDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改外卖柜成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改外卖柜失败！");
        }
    }

    @Operation(summary = "添加新外卖柜")
    @PostMapping
    public BaseResponse<?> addCabinet(AddCabinetDto addCabinetDto) {
        boolean result = cabinetService.addCabinet(addCabinetDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增外卖柜成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增外卖柜失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个外卖柜")
    public BaseResponse<?> deleteCabinet(@PathVariable Integer id) {
        boolean result = cabinetService.deleteCabinet(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除外卖柜成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除外卖柜失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除外卖柜")
    public BaseResponse<?> deleteBatchCabinet(Integer... cabinetIds) {
        boolean result = cabinetService.deleteBatchCabinet(cabinetIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除外卖柜成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除外卖柜失败！");
        }
    }


}
