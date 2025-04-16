package com.qcx.property.domain.dto.cleaning;

import com.qcx.property.domain.entity.CleaningAppointment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 更新保洁预约 dto
 * @author: yannqing
 * @create: 2025-02-09 10:45
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCleaningAppointmentDto", description = "修改保洁预约信息对象")
public class UpdateCleaningAppointmentDto implements Serializable {
    /**
     * 预约id
     */
    @Schema(description = "预约id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer serviceId;

    /**
     * 预约时间
     */
    @Schema(description = "预约时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date appointmentTime;

    /**
     * 服务地址
     */
    @Schema(description = "服务地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String contactName;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String contactPhone;

    /**
     * 状态(0待确认,1已确认,2已完成,3已取消,-1拒绝)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

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
     * 支付状态(0未支付,1已支付)
     */
    @Schema(description = "支付状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer paymentStatus;

    /**
     * 总金额
     */
    @Schema(description = "总金额", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal totalAmount;

    public static CleaningAppointment objToCleaningAppointment(UpdateCleaningAppointmentDto updateCleaningAppointmentDto) {
        if (updateCleaningAppointmentDto == null) {
            return null;
        }

        CleaningAppointment cleaningAppointment = new CleaningAppointment();
        BeanUtils.copyProperties(updateCleaningAppointmentDto, cleaningAppointment);
        return cleaningAppointment;
    }
} 