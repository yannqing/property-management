package com.qcx.property.enums.takeouts;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TakeoutsPlatformType {
    ELEMENT(0, "饿了么"),
    MW_TR(1, "美团外卖"),
    STAR_BUCKS(2, "星巴克（专星送）"),
    KFC(3, "肯德基（宅急送）"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (TakeoutsPlatformType type : TakeoutsPlatformType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
