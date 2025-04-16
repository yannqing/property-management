package com.qcx.property.domain.vo;

import com.qcx.property.domain.entity.CleaningAppointment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 保洁预约视图对象
 * @author: yannqing
 * @create: 2025-02-09 10:55
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "CleaningAppointmentVO", description = "保洁预约视图对象")
public class CleaningAppointmentVO implements Serializable {
    /**
     * 预约id
     */
    @Schema(description = "预约id")
    private Integer id;

    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id")
    private Integer serviceId;

    /**
     * 保洁服务名称
     */
    @Schema(description = "保洁服务名称")
    private String serviceName;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Integer userId;

    /**
     * 用户姓名
     */
    @Schema(description = "用户姓名")
    private String userName;

    /**
     * 预约时间
     */
    @Schema(description = "预约时间")
    private Date appointmentTime;

    /**
     * 服务地址
     */
    @Schema(description = "服务地址")
    private String address;

    /**
     * 联系人姓名
     */
    @Schema(description = "联系人姓名")
    private String contactName;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String contactPhone;

    /**
     * 状态(0待确认,1已确认,2已完成,3已取消,-1拒绝)
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 状态描述
     */
    @Schema(description = "状态描述")
    private String statusDesc;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remarks;

    /**
     * 支付方式(0现金,1支付宝,-1微信支付)
     */
    @Schema(description = "支付方式")
    private Integer paymentMethod;

    /**
     * 支付方式描述
     */
    @Schema(description = "支付方式描述")
    private String paymentMethodDesc;

    /**
     * 支付状态(0未支付,1已支付)
     */
    @Schema(description = "支付状态")
    private Integer paymentStatus;

    /**
     * 支付状态描述
     */
    @Schema(description = "支付状态描述")
    private String paymentStatusDesc;

    /**
     * 总金额
     */
    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    public static CleaningAppointmentVO entityToVO(CleaningAppointment cleaningAppointment) {
        if (cleaningAppointment == null) {
            return null;
        }
        CleaningAppointmentVO vo = new CleaningAppointmentVO();
        BeanUtils.copyProperties(cleaningAppointment, vo);
        
        // 状态描述
        if (cleaningAppointment.getStatus() != null) {
            switch (cleaningAppointment.getStatus()) {
                case 0 -> vo.setStatusDesc("待确认");
                case 1 -> vo.setStatusDesc("已确认");
                case 2 -> vo.setStatusDesc("已完成");
                case 3 -> vo.setStatusDesc("已取消");
                case -1 -> vo.setStatusDesc("已拒绝");
                default -> vo.setStatusDesc("未知状态");
            }
        }
        
        // 支付方式描述
        if (cleaningAppointment.getPaymentMethod() != null) {
            switch (cleaningAppointment.getPaymentMethod()) {
                case 0 -> vo.setPaymentMethodDesc("现金");
                case 1 -> vo.setPaymentMethodDesc("支付宝");
                case -1 -> vo.setPaymentMethodDesc("微信支付");
                default -> vo.setPaymentMethodDesc("未知支付方式");
            }
        }
        
        // 支付状态描述
        if (cleaningAppointment.getPaymentStatus() != null) {
            switch (cleaningAppointment.getPaymentStatus()) {
                case 0 -> vo.setPaymentStatusDesc("未支付");
                case 1 -> vo.setPaymentStatusDesc("已支付");
                default -> vo.setPaymentStatusDesc("未知支付状态");
            }
        }
        
        return vo;
    }
} 