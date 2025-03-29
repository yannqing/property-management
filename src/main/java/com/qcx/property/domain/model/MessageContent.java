package com.qcx.property.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息内容自定义封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageContent<T> {
    private String notify;
    private T data;
    private RoomModel roomModel;
}
