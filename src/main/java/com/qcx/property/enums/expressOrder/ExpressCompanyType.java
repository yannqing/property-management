package com.qcx.property.enums.expressOrder;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpressCompanyType {
    SF(1, "顺丰快递"),
    EMS(2, "中国邮政"),
    ZTO(3, "中通快递"),
    JD(4, "京东物流"),
    STO(5, "申通快递"),
    YTO(6, "圆通快递"),
    YD(7, "韵达快递")
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (ExpressCompanyType type : ExpressCompanyType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
