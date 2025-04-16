package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.committee.AddCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMemberDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMemberDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.CommitteeMemberVO;
import com.qcx.property.service.CommitteeMemberService;
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
import java.util.List;

/**
 * @description: 委员会成员控制器
 * @author: yannqing
 * @create: 2025-04-17 10:00
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Tag(name = "委员会成员管理")
@RestController
@RequestMapping("/committee-member")
@SecurityRequirement(name = "Bearer Authentication")
public class CommitteeMemberController {

    @Resource
    private CommitteeMemberService committeeMemberService;

    @Operation(summary = "分页查询委员会成员列表")
    @GetMapping
    public BaseResponse<Page<CommitteeMemberVO>> pageCommitteeMember(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer committeeId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date joinDateStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date joinDateEnd,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String responsibilities,
            @RequestParam(required = false, defaultValue = "1") int current,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        
        QueryCommitteeMemberDto queryCommitteeMemberDto = new QueryCommitteeMemberDto();
        queryCommitteeMemberDto.setId(id);
        queryCommitteeMemberDto.setCommitteeId(committeeId);
        queryCommitteeMemberDto.setUserId(userId);
        queryCommitteeMemberDto.setPosition(position);
        queryCommitteeMemberDto.setJoinDateStart(joinDateStart);
        queryCommitteeMemberDto.setJoinDateEnd(joinDateEnd);
        queryCommitteeMemberDto.setStatus(status);
        queryCommitteeMemberDto.setResponsibilities(responsibilities);
        queryCommitteeMemberDto.setCurrent(current);
        queryCommitteeMemberDto.setPageSize(pageSize);
        
        Page<CommitteeMemberVO> page = committeeMemberService.pageCommitteeMember(queryCommitteeMemberDto);
        return ResultUtils.success(Code.SUCCESS, page, "查询委员会成员列表成功");
    }

    @Operation(summary = "根据ID查询委员会成员详情")
    @GetMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "成员ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<CommitteeMemberVO> getCommitteeMemberById(@PathVariable Integer id) {
        CommitteeMemberVO committeeMemberVO = committeeMemberService.getCommitteeMemberById(id);
        return ResultUtils.success(Code.SUCCESS, committeeMemberVO, "查询委员会成员详情成功");
    }

    @Operation(summary = "根据委员会ID查询所有成员")
    @GetMapping("/by-committee/{committeeId}")
    @Parameters(@Parameter(name = "committeeId", description = "委员会ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<List<CommitteeMemberVO>> getCommitteeMembersByCommitteeId(@PathVariable Integer committeeId) {
        List<CommitteeMemberVO> members = committeeMemberService.getCommitteeMembersByCommitteeId(committeeId);
        return ResultUtils.success(Code.SUCCESS, members, "查询委员会所有成员成功");
    }

    @Operation(summary = "根据用户ID查询担任的委员会职务")
    @GetMapping("/by-user/{userId}")
    @Parameters(@Parameter(name = "userId", description = "用户ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<List<CommitteeMemberVO>> getCommitteeMembersByUserId(@PathVariable Integer userId) {
        List<CommitteeMemberVO> members = committeeMemberService.getCommitteeMembersByUserId(userId);
        return ResultUtils.success(Code.SUCCESS, members, "查询用户担任的委员会职务成功");
    }

    @Operation(summary = "新增委员会成员")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> addCommitteeMember(@RequestBody AddCommitteeMemberDto addCommitteeMemberDto) {
        boolean result = committeeMemberService.addCommitteeMember(addCommitteeMemberDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增委员会成员成功");
        }
        return ResultUtils.failure("新增委员会成员失败");
    }

    @Operation(summary = "更新委员会成员")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> updateCommitteeMember(@RequestBody UpdateCommitteeMemberDto updateCommitteeMemberDto) {
        boolean result = committeeMemberService.updateCommitteeMember(updateCommitteeMemberDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "更新委员会成员成功");
        }
        return ResultUtils.failure("更新委员会成员失败");
    }

    @Operation(summary = "删除委员会成员")
    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "成员ID", required = true, in = ParameterIn.PATH))
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> deleteCommitteeMember(@PathVariable Integer id) {
        boolean result = committeeMemberService.deleteCommitteeMember(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除委员会成员成功");
        }
        return ResultUtils.failure("删除委员会成员失败");
    }

    @Operation(summary = "批量删除委员会成员")
    @DeleteMapping("/batch")
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> batchDeleteCommitteeMember(@RequestParam Integer[] ids) {
        boolean result = committeeMemberService.batchDeleteCommitteeMember(ids);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除委员会成员成功");
        }
        return ResultUtils.failure("批量删除委员会成员失败");
    }
} 