package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.annotation.AuthCheck;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.auth.RegisterDto;
import com.qcx.property.domain.dto.message.QueryApprovalDto;
import com.qcx.property.domain.model.ApprovalModel;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.AuthService;
import com.qcx.property.service.MessageService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 管理员
 * @author: yannqing
 * @create: 2025-01-10 15:18
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "管理员")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private MessageService messageService;

    @Operation(summary = "管理员获取所有审批列表")
    @PostMapping("/get/approvals")
    public BaseResponse<?> getApprovals(QueryApprovalDto queryApprovalDto) {
        Page<ApprovalModel> approvals = messageService.getApprovals(queryApprovalDto);
        return ResultUtils.success(Code.SUCCESS, approvals);
    }
}
