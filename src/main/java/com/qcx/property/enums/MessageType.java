package com.qcx.property.enums;

import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
    EXPRESS(1, "快递通知"),
    SMS(2, "短信通知"),
    EMAIL(3, "邮件通知"),
    TAKEOUT(4, "外卖通知")
    ;

    private final int id;
    private final String remark;

    public static String getMessageTypeById(int id) {
        for (MessageType type : MessageType.values()) {
            if (type.getId() == id) {
                return type.getRemark();
            }
        }
        throw new BusinessException(ErrorType.SYSTEM_ERROR);
    }
}
