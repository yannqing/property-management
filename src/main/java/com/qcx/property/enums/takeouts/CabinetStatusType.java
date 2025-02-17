package com.qcx.property.enums.takeouts;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CabinetStatusType {
    AVAILABLE(0, "可用"),
    OCCUPIED(1, "已占用"),
    EXCEPTION(2, "异常"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (CabinetStatusType type : CabinetStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
