package com.qcx.property.enums.activity;

import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommunityActivityType {

    CULTURE_ACTIVITY(0, "文化活动"),
    SPORTS_ACTIVITY(1, "体育活动"),
    VOLUNTEERING(2, "志愿服务"),
    HOLIDAY_CELEBRATION(3, "节日庆祝"),
    EDUCATIONAL_LECTURE(4, "教育讲座"),
    ;

    private final int id;
    private final String remark;

    public static String getRemarkById(int id) {
        for (CommunityActivityType type : CommunityActivityType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
