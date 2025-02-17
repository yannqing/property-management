package com.qcx.property.enums.takeouts;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TakeoutsStatusType {
    ORDERED(0, "已下单"),
    ORDER_RECEIVED(1, "骑手已接单"),
    DELIVERY(2, "派送中"),
    DELIVERED(3, "已送达"),
    FINISHED(4, "已完成"),
    CANCELED(5, "已取消"),
    EXCEPTION(6, "异常")
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (TakeoutsStatusType type : TakeoutsStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
