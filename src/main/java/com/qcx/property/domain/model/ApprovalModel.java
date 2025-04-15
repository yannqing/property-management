package com.qcx.property.domain.model;

import com.qcx.property.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 待审批返回 vo 封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalModel implements Serializable {
    private User user;
    private String notify;
    private RoomModel roomModel;
}
