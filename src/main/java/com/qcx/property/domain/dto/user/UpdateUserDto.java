package com.qcx.property.domain.dto.user;

import com.qcx.property.domain.entity.User;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

/**
 * @description: 修改用户信息的 dto（管理员）
 * @author: yannqing
 * @create: 2025-01-13 10:36
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class UpdateUserDto {
    private Integer userId;
    private String username;
    private String nickName;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private List<Integer> roleIds;
    private Integer enabled;

    public static User dtoToUser(UpdateUserDto updateUserDto) {
        Optional.ofNullable(updateUserDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        User updpateUser = new User();
        BeanUtils.copyProperties(updateUserDto, updpateUser);
        return updpateUser;
    }
}
