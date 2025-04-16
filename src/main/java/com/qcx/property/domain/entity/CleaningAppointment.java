package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName cleaning_appointment
 */
@TableName(value ="cleaning_appointment")
@Data
public class CleaningAppointment implements Serializable {
    /**
     * 预约id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 保洁服务id
     */
    @TableField(value = "serviceId")
    private Integer serviceId;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userId;

    /**
     * 预约时间
     */
    @TableField(value = "appointmentTime")
    private Date appointmentTime;

    /**
     * 服务地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联系人姓名
     */
    @TableField(value = "contactName")
    private String contactName;

    /**
     * 联系电话
     */
    @TableField(value = "contactPhone")
    private String contactPhone;

    /**
     * 状态(0待确认,1已确认,2已完成,3已取消,-1拒绝)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 支付方式(0现金,1支付宝,-1微信支付)
     */
    @TableField(value = "paymentMethod")
    private Integer paymentMethod;

    /**
     * 支付状态(0未支付,1已支付)
     */
    @TableField(value = "paymentStatus")
    private Integer paymentStatus;

    /**
     * 总金额
     */
    @TableField(value = "totalAmount")
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(value = "isDelete")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CleaningAppointment other = (CleaningAppointment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAppointmentTime() == null ? other.getAppointmentTime() == null : this.getAppointmentTime().equals(other.getAppointmentTime()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getPaymentMethod() == null ? other.getPaymentMethod() == null : this.getPaymentMethod().equals(other.getPaymentMethod()))
            && (this.getPaymentStatus() == null ? other.getPaymentStatus() == null : this.getPaymentStatus().equals(other.getPaymentStatus()))
            && (this.getTotalAmount() == null ? other.getTotalAmount() == null : this.getTotalAmount().equals(other.getTotalAmount()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAppointmentTime() == null) ? 0 : getAppointmentTime().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getPaymentMethod() == null) ? 0 : getPaymentMethod().hashCode());
        result = prime * result + ((getPaymentStatus() == null) ? 0 : getPaymentStatus().hashCode());
        result = prime * result + ((getTotalAmount() == null) ? 0 : getTotalAmount().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", userId=").append(userId);
        sb.append(", appointmentTime=").append(appointmentTime);
        sb.append(", address=").append(address);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", status=").append(status);
        sb.append(", remarks=").append(remarks);
        sb.append(", paymentMethod=").append(paymentMethod);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
} 