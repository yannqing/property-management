package com.qcx.property.domain.dto.cleaning;

import com.qcx.property.domain.entity.CleaningAppointment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 新增保洁预约 dto
 * @author: yannqing
 * @create: 2025-02-09 10:40
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddCleaningAppointmentDto", description = "新增保洁预约请求参数")
public class AddCleaningAppointmentDto implements Serializable {
    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer serviceId;

    /**
     * 用户id
     */
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    /**
     * 预约时间
     */
    @Schema(description = "预约时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private Date appointmentTime;

    /**
     * 服务地址
     */
    @Schema(description = "服务地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contactName;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contactPhone;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String remarks;

    /**
     * 支付方式(0现金,1支付宝,-1微信支付)
     */
    @Schema(description = "支付方式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer paymentMethod;

    /**
     * 总金额
     */
    @Schema(description = "总金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal totalAmount;

    @Serial
    private static final long serialVersionUID = 1L;

    public static CleaningAppointment objToCleaningAppointment(AddCleaningAppointmentDto addCleaningAppointmentDto) {
        if (addCleaningAppointmentDto == null) {
            return null;
        }

        CleaningAppointment cleaningAppointment = new CleaningAppointment();
        BeanUtils.copyProperties(addCleaningAppointmentDto, cleaningAppointment);
        cleaningAppointment.setStatus(0); // 默认状态：待确认
        cleaningAppointment.setPaymentStatus(0); // 默认支付状态：未支付
        return cleaningAppointment;
    }
} 