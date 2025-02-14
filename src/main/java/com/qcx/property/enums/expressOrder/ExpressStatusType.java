package com.qcx.property.enums.expressOrder;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpressStatusType {
    SF(1, "待处理"),
    EMS(2, "已取件"),
    ZTO(3, "已存放"),
    JD(4, "已完成"),
    STO(5, "已取消"),
    YTO(6, "异常"),
    YD(7, "处理中")
    ;

    private final int id;
    private final String remark;

    public static String getExpressStatusById(int id) {
        for (ExpressStatusType type : ExpressStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
