package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.cost.AddCostDto;
import com.qcx.property.domain.dto.cost.QueryCostDto;
import com.qcx.property.domain.dto.cost.UpdateCostDto;
import com.qcx.property.domain.entity.Cost;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.CostService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 费用管理
 * @author: yannqing
 * @create: 2025-02-08 17:14
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "费用管理")
@RestController
@RequestMapping("/cost")
public class CostController {

    @Resource
    private CostService costService;

    @Operation(summary = "查询所有费用信息")
    @GetMapping
    public BaseResponse<?> getAllCosts(QueryCostDto queryCostDto) {
        Page<Cost> costList = costService.getAllCosts(queryCostDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部费用成功！");
    }

    @PutMapping
    @Operation(summary = "更新费用信息")
    public BaseResponse<?> updateCost(UpdateCostDto updateCostDto) {
        boolean result = costService.updateCost(updateCostDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改费用信息失败！");
        }
    }

    @Operation(summary = "添加新费用")
    @PostMapping
    public BaseResponse<?> addCost(AddCostDto addCostDto) {
        boolean result = costService.addCost(addCostDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增费用信息失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "费用信息 id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个费用信息")
    public BaseResponse<?> deleteCost(@PathVariable Integer id) {
        boolean result = costService.deleteCost(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除费用信息失败！");
        }
    }

    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "costIds", description = "费用信息 id 数组", required = true))
    @Operation(summary = "批量删除费用信息")
    public BaseResponse<?> deleteBatchCost(Integer... costIds) {
        boolean result = costService.deleteBatchCost(costIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除费用信息成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除费用信息失败！");
        }
    }


}
