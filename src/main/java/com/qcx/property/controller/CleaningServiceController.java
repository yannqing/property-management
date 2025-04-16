package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.cleaning.AddCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningServiceDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.CleaningServiceVO;
import com.qcx.property.service.CleaningServiceService;
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
 * @description: 保洁服务管理
 * @author: yannqing
 * @create: 2025-02-09 11:35
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "保洁服务管理")
@RestController
@RequestMapping("/cleaning/service")
public class CleaningServiceController {

    @Resource
    private CleaningServiceService cleaningServiceService;

    @Operation(summary = "查询所有保洁服务")
    @GetMapping
    public BaseResponse<?> getAllCleaningServices(QueryCleaningServiceDto queryCleaningServiceDto) {
        Page<CleaningServiceVO> page = cleaningServiceService.getAllCleaningServices(queryCleaningServiceDto);
        return ResultUtils.success(Code.SUCCESS, page, "查询保洁服务成功！");
    }

    @Operation(summary = "根据id查询保洁服务")
    @GetMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "保洁服务id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> getCleaningServiceById(@PathVariable Integer id) {
        CleaningServiceVO cleaningServiceVO = cleaningServiceService.getCleaningServiceById(id);
        return ResultUtils.success(Code.SUCCESS, cleaningServiceVO, "查询保洁服务成功！");
    }

    @Operation(summary = "获取所有可用的保洁服务")
    @GetMapping("/enabled")
    public BaseResponse<?> getAllEnabledCleaningServices() {
        List<CleaningServiceVO> list = cleaningServiceService.getAllEnabledCleaningServices();
        return ResultUtils.success(Code.SUCCESS, list, "查询可用保洁服务成功！");
    }

    @Operation(summary = "添加保洁服务")
    @PostMapping
    public BaseResponse<?> addCleaningService(@RequestBody AddCleaningServiceDto addCleaningServiceDto) {
        boolean result = cleaningServiceService.addCleaningService(addCleaningServiceDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "添加保洁服务成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "添加保洁服务失败！");
        }
    }

    @Operation(summary = "更新保洁服务")
    @PutMapping
    public BaseResponse<?> updateCleaningService(@RequestBody UpdateCleaningServiceDto updateCleaningServiceDto) {
        boolean result = cleaningServiceService.updateCleaningService(updateCleaningServiceDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "更新保洁服务成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "更新保洁服务失败！");
        }
    }

    @Operation(summary = "删除保洁服务")
    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "保洁服务id", required = true, in = ParameterIn.PATH))
    public BaseResponse<?> deleteCleaningService(@PathVariable Integer id) {
        boolean result = cleaningServiceService.deleteCleaningService(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除保洁服务成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除保洁服务失败！");
        }
    }

    @Operation(summary = "批量删除保洁服务")
    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "ids", description = "保洁服务id数组", required = true))
    public BaseResponse<?> batchDeleteCleaningService(@RequestParam Integer[] ids) {
        boolean result = cleaningServiceService.batchDeleteCleaningService(ids);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除保洁服务成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除保洁服务失败！");
        }
    }
} 