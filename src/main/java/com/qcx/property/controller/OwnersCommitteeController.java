package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.committee.AddOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.QueryOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.UpdateOwnersCommitteeDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.OwnersCommitteeVO;
import com.qcx.property.service.OwnersCommitteeService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @description: 业主委员会控制器
 * @author: yannqing
 * @create: 2025-04-16 15:00
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Tag(name = "业主委员会管理")
@RestController
@RequestMapping("/owners-committee")
@SecurityRequirement(name = "Bearer Authentication")
public class OwnersCommitteeController {

    @Resource
    private OwnersCommitteeService ownersCommitteeService;

    @Operation(summary = "分页查询业主委员会列表")
    @GetMapping
    public BaseResponse<Page<OwnersCommitteeVO>> pageOwnersCommittee(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date establishmentDateStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date establishmentDateEnd,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date termStartDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date termEndDate,
            @RequestParam(required = false, defaultValue = "1") int current,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {

        QueryOwnersCommitteeDto queryOwnersCommitteeDto = new QueryOwnersCommitteeDto();
        queryOwnersCommitteeDto.setId(id);
        queryOwnersCommitteeDto.setName(name);
        queryOwnersCommitteeDto.setStatus(status);
        queryOwnersCommitteeDto.setDescription(description);
        queryOwnersCommitteeDto.setEstablishmentDateStart(establishmentDateStart);
        queryOwnersCommitteeDto.setEstablishmentDateEnd(establishmentDateEnd);
        queryOwnersCommitteeDto.setTermStartDate(termStartDate);
        queryOwnersCommitteeDto.setTermEndDate(termEndDate);
        queryOwnersCommitteeDto.setCurrent(current);
        queryOwnersCommitteeDto.setPageSize(pageSize);

        Page<OwnersCommitteeVO> page = ownersCommitteeService.pageOwnersCommittee(queryOwnersCommitteeDto);
        return ResultUtils.success(Code.SUCCESS, page, "查询业主委员会列表成功");
    }

    @Operation(summary = "根据ID查询业主委员会详情")
    @GetMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "委员会ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<OwnersCommitteeVO> getOwnersCommitteeById(@PathVariable Integer id) {
        OwnersCommitteeVO ownersCommitteeVO = ownersCommitteeService.getOwnersCommitteeById(id);
        return ResultUtils.success(Code.SUCCESS, ownersCommitteeVO, "查询业主委员会详情成功");
    }

    @Operation(summary = "新增业主委员会")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> addOwnersCommittee(@RequestBody AddOwnersCommitteeDto addOwnersCommitteeDto) {
        boolean result = ownersCommitteeService.addOwnersCommittee(addOwnersCommitteeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增业主委员会成功");
        }
        return ResultUtils.failure("新增业主委员会失败");
    }

    @Operation(summary = "更新业主委员会")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> updateOwnersCommittee(@RequestBody UpdateOwnersCommitteeDto updateOwnersCommitteeDto) {
        boolean result = ownersCommitteeService.updateOwnersCommittee(updateOwnersCommitteeDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "更新业主委员会成功");
        }
        return ResultUtils.failure("更新业主委员会失败");
    }

    @Operation(summary = "删除业主委员会")
    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "委员会ID", required = true, in = ParameterIn.PATH))
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> deleteOwnersCommittee(@PathVariable Integer id) {
        boolean result = ownersCommitteeService.deleteOwnersCommittee(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除业主委员会成功");
        }
        return ResultUtils.failure("删除业主委员会失败");
    }

    @Operation(summary = "批量删除业主委员会")
    @DeleteMapping("/batch")
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> batchDeleteOwnersCommittee(@RequestParam Integer[] ids) {
        boolean result = ownersCommitteeService.batchDeleteOwnersCommittee(ids);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除业主委员会成功");
        }
        return ResultUtils.failure("批量删除业主委员会失败");
    }
} 