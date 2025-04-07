package com.qcx.property.controller;

import com.qcx.property.annotation.AuthCheck;
import com.qcx.property.domain.dto.auth.RegisterDto;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.AuthService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 认证
 * @author: yannqing
 * @create: 2025-01-10 15:18
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "用户认证")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @Operation(summary = "注册")
    @PostMapping("/register")
    public BaseResponse<?> register(@RequestBody RegisterDto registerDto) {
        boolean result = authService.register(registerDto);
        if (result) {
            return ResultUtils.success(String.format("用户%s注册成功", registerDto.getUsername()));
        } else {
            return ResultUtils.failure(String.format("用户%s注册失败", registerDto.getUsername()));
        }
    }

    @AuthCheck(code = "AUTH_CHECK_REGISTER")
    @Operation(summary = "管理员审批")
    @Parameters({
            @Parameter(name = "id", description = "消息id", required = true),
            @Parameter(name = "isPass", description = "是否通过", required = true),
            @Parameter(name = "roomNum", description = "房间号", required = true),
    })
    @PostMapping("/check")
    public BaseResponse<?> check(Integer id, boolean isPass, String roomNum) {
        boolean result = authService.checkRegister(id, isPass, roomNum);
        if (result) {
            return ResultUtils.success("管理员审批成功");
        } else {
            return ResultUtils.failure("管理员审批失败");
        }
    }
}
