package com.qcx.property.service;

import com.qcx.property.domain.dto.auth.RegisterDto;

/**
 * @description: 认证接口
 * @author: yannqing
 * @create: 2025-01-14 16:33
 **/
public interface AuthService {

    /**
     * 注册
     * @param registerDto 注册 dto
     * @return 返回注册结果
     */
    boolean register(RegisterDto registerDto);

    /**
     * 管理员审批业主注册
     * @param id 消息id
     * @param isPass 是否通过
     * @param roomNum 房间号
     * @return 返回审批结果
     */
    boolean checkRegister(Integer id, boolean isPass, String roomNum);
}
