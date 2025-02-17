package com.qcx.property.enums.activity;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityRegistrationStatusType {
//    1:已报名 2:已签到 3:已取消
    REGISTERED(1, "已报名"),
    SIGNED_IN(2, "已签到"),
    CANCELED(3, "已取消"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (ActivityRegistrationStatusType type : ActivityRegistrationStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
