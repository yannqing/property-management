package com.qcx.property.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomModel implements Serializable {

    /**
     * 房产证照片
     */
    private String roomCertificate;

    /**
     * 身份证照片
     */
    private String IDCard;

    /**
     * 银行卡号
     */
    private String bankCardNum;

    /**
     * 银行名称
     */
    private String bankName;
}
