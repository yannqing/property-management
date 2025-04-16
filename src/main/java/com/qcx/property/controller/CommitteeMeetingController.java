package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.committee.AddCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.QueryCommitteeMeetingDto;
import com.qcx.property.domain.dto.committee.UpdateCommitteeMeetingDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.CommitteeMeetingVO;
import com.qcx.property.service.CommitteeMeetingService;
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
 * @description: 委员会会议控制器
 * @author: yannqing
 * @create: 2025-04-17 10:30
 * @from: <更多资料：yannqing.com>
 **/
@Slf4j
@Tag(name = "委员会会议管理")
@RestController
@RequestMapping("/committee-meeting")
@SecurityRequirement(name = "Bearer Authentication")
public class CommitteeMeetingController {

    @Resource
    private CommitteeMeetingService committeeMeetingService;

    @Operation(summary = "分页查询委员会会议列表")
    @GetMapping
    public BaseResponse<Page<CommitteeMeetingVO>> pageCommitteeMeeting(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer committeeId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd['T'HH:mm:ss]") Date meetingDateStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd['T'HH:mm:ss]") Date meetingDateEnd,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer organizerId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false, defaultValue = "1") int current,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
            
        QueryCommitteeMeetingDto queryCommitteeMeetingDto = new QueryCommitteeMeetingDto();
        queryCommitteeMeetingDto.setId(id);
        queryCommitteeMeetingDto.setCommitteeId(committeeId);
        queryCommitteeMeetingDto.setTitle(title);
        queryCommitteeMeetingDto.setMeetingDateStart(meetingDateStart);
        queryCommitteeMeetingDto.setMeetingDateEnd(meetingDateEnd);
        queryCommitteeMeetingDto.setLocation(location);
        queryCommitteeMeetingDto.setOrganizerId(organizerId);
        queryCommitteeMeetingDto.setStatus(status);
        queryCommitteeMeetingDto.setCurrent(current);
        queryCommitteeMeetingDto.setPageSize(pageSize);
            
        Page<CommitteeMeetingVO> page = committeeMeetingService.pageCommitteeMeeting(queryCommitteeMeetingDto);
        return ResultUtils.success(Code.SUCCESS, page, "查询委员会会议列表成功");
    }

    @Operation(summary = "根据ID查询委员会会议详情")
    @GetMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "会议ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<CommitteeMeetingVO> getCommitteeMeetingById(@PathVariable Integer id) {
        CommitteeMeetingVO committeeMeetingVO = committeeMeetingService.getCommitteeMeetingById(id);
        return ResultUtils.success(Code.SUCCESS, committeeMeetingVO, "查询委员会会议详情成功");
    }

    @Operation(summary = "根据委员会ID查询所有会议")
    @GetMapping("/by-committee/{committeeId}")
    @Parameters(@Parameter(name = "committeeId", description = "委员会ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<List<CommitteeMeetingVO>> getCommitteeMeetingsByCommitteeId(@PathVariable Integer committeeId) {
        List<CommitteeMeetingVO> meetings = committeeMeetingService.getCommitteeMeetingsByCommitteeId(committeeId);
        return ResultUtils.success(Code.SUCCESS, meetings, "查询委员会所有会议成功");
    }

    @Operation(summary = "根据组织者ID查询所有会议")
    @GetMapping("/by-organizer/{organizerId}")
    @Parameters(@Parameter(name = "organizerId", description = "组织者ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<List<CommitteeMeetingVO>> getCommitteeMeetingsByOrganizerId(@PathVariable Integer organizerId) {
        List<CommitteeMeetingVO> meetings = committeeMeetingService.getCommitteeMeetingsByOrganizerId(organizerId);
        return ResultUtils.success(Code.SUCCESS, meetings, "查询组织者的所有会议成功");
    }

    @Operation(summary = "统计委员会会议数量")
    @GetMapping("/count/{committeeId}")
    @Parameters(@Parameter(name = "committeeId", description = "委员会ID", required = true, in = ParameterIn.PATH))
    public BaseResponse<Integer> countCommitteeMeetingsByCommitteeId(@PathVariable Integer committeeId) {
        int count = committeeMeetingService.countCommitteeMeetingsByCommitteeId(committeeId);
        return ResultUtils.success(Code.SUCCESS, count, "统计委员会会议数量成功");
    }

    @Operation(summary = "新增委员会会议")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> addCommitteeMeeting(@RequestBody AddCommitteeMeetingDto addCommitteeMeetingDto) {
        boolean result = committeeMeetingService.addCommitteeMeeting(addCommitteeMeetingDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增委员会会议成功");
        }
        return ResultUtils.failure("新增委员会会议失败");
    }

    @Operation(summary = "更新委员会会议")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> updateCommitteeMeeting(@RequestBody UpdateCommitteeMeetingDto updateCommitteeMeetingDto) {
        boolean result = committeeMeetingService.updateCommitteeMeeting(updateCommitteeMeetingDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "更新委员会会议成功");
        }
        return ResultUtils.failure("更新委员会会议失败");
    }

    @Operation(summary = "删除委员会会议")
    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "会议ID", required = true, in = ParameterIn.PATH))
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> deleteCommitteeMeeting(@PathVariable Integer id) {
        boolean result = committeeMeetingService.deleteCommitteeMeeting(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除委员会会议成功");
        }
        return ResultUtils.failure("删除委员会会议失败");
    }

    @Operation(summary = "批量删除委员会会议")
    @DeleteMapping("/batch")
    @PreAuthorize("hasAnyAuthority('admin', 'property_manager')")
    public BaseResponse<?> batchDeleteCommitteeMeeting(@RequestParam Integer[] ids) {
        boolean result = committeeMeetingService.batchDeleteCommitteeMeeting(ids);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除委员会会议成功");
        }
        return ResultUtils.failure("批量删除委员会会议失败");
    }
} 