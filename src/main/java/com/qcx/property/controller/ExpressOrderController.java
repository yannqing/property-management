package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.ExpressOrder.AddExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.QueryExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.UpdateExpressOrderDto;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.ExpressOrder.ExpressOrderVo;
import com.qcx.property.service.ExpressOrderService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 快递订单管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "快递订单管理")
@RestController
@RequestMapping("/express_order")
public class ExpressOrderController {

    @Resource
    private ExpressOrderService expressOrderService;

    @Operation(summary = "查询所有快递订单（仅限管理员）")
    @GetMapping
    public BaseResponse<?> getAllExpressOrders(QueryExpressOrderDto queryExpressOrderDto) {
        Page<ExpressOrder> costList = expressOrderService.getAllExpressOrders(queryExpressOrderDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部快递订单成功！");
    }

    @Operation(summary = "查询所有快递订单（个人）")
    @GetMapping("/myself")
    public BaseResponse<?> getMyselfExpressOrders(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        Page<ExpressOrderVo> costList = expressOrderService.getMyselfExpressOrders(pageRequest, request);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部快递订单成功！");
    }

    @PutMapping
    @Operation(summary = "更新快递订单（仅限管理员）")
    public BaseResponse<?> updateExpressOrder(UpdateExpressOrderDto updateExpressOrderDto) {
        boolean result = expressOrderService.updateExpressOrder(updateExpressOrderDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改快递订单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改快递订单失败！");
        }
    }

    @Operation(summary = "添加新快递订单")
    @PostMapping
    public BaseResponse<?> addExpressOrder(AddExpressOrderDto addExpressOrderDto) {
        boolean result = expressOrderService.addExpressOrder(addExpressOrderDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增快递订单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增快递订单失败！");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除单个快递订单")
    public BaseResponse<?> deleteExpressOrder(@PathVariable Integer id) {
        boolean result = expressOrderService.deleteExpressOrder(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除快递订单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除快递订单失败！");
        }
    }

    @DeleteMapping("/batch")
    @Operation(summary = "批量删除快递订单")
    public BaseResponse<?> deleteBatchExpressOrder(Integer... costTypeIds) {
        boolean result = expressOrderService.deleteBatchExpressOrder(costTypeIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除快递订单成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除快递订单失败！");
        }
    }


}
