package com.qcx.property.enums.expressOrder;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpressStatusType {
    PENDING(1, "运输中"),
    PICKED_UP(2, "已取件"),
    STORED(3, "已存放"),
    FINISHED(4, "已完成"),
    CANCELED(5, "已取消"),
    EXCEPTION(6, "异常"),
    PROCESSING(7, "待处理")
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (ExpressStatusType type : ExpressStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
