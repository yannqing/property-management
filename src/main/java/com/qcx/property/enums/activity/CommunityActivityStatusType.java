package com.qcx.property.enums.activity;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.takeouts.CabinetStatusType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommunityActivityStatusType {
    DRAFT(0, "草稿"),
    REGISTRATION(1, "报名中"),
    PROCESSING(2, "进行中"),
    ENDED(3, "已结束"),
    CANCELED(4, "已取消"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (CommunityActivityStatusType type : CommunityActivityStatusType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
