package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 外卖代收记录表
 * @TableName takeout_pickup_record
 */
@TableName(value ="takeout_pickup_record")
@Data
public class TakeoutPickupRecord implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 外卖订单号
     */
    @TableField(value = "orderNumber")
    private String orderNumber;

    /**
     * 收件人id
     */
    @TableField(value = "userId")
    private Integer userId;

    /**
     * 外卖地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 快递员id
     */
    @TableField(value = "courierId")
    private Integer courierId;

    /**
     * 外卖平台
     */
    @TableField(value = "platform")
    private Integer platform;

    /**
     * 状态(已下单/骑手已接单/派送中/已送达/已完成/已取消/异常)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 备注信息
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 柜号
     */
    @TableField(value = "cabinetNumber")
    private Integer cabinetNumber;

    /**
     * 预计送达时间
     */
    @TableField(value = "estimatedDeliveryTime")
    private Date estimatedDeliveryTime;

    /**
     * 送达通知时间
     */
    @TableField(value = "notifyTime")
    private Date notifyTime;

    /**
     * 领取时间
     */
    @TableField(value = "pickupTime")
    private Date pickupTime;

    /**
     * 外卖超时时间
     */
    @TableField(value = "timeoutTime")
    private Date timeoutTime;

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

    @Serial
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
        TakeoutPickupRecord other = (TakeoutPickupRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCourierId() == null ? other.getCourierId() == null : this.getCourierId().equals(other.getCourierId()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCabinetNumber() == null ? other.getCabinetNumber() == null : this.getCabinetNumber().equals(other.getCabinetNumber()))
            && (this.getEstimatedDeliveryTime() == null ? other.getEstimatedDeliveryTime() == null : this.getEstimatedDeliveryTime().equals(other.getEstimatedDeliveryTime()))
            && (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getPickupTime() == null ? other.getPickupTime() == null : this.getPickupTime().equals(other.getPickupTime()))
            && (this.getTimeoutTime() == null ? other.getTimeoutTime() == null : this.getTimeoutTime().equals(other.getTimeoutTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCourierId() == null) ? 0 : getCourierId().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCabinetNumber() == null) ? 0 : getCabinetNumber().hashCode());
        result = prime * result + ((getEstimatedDeliveryTime() == null) ? 0 : getEstimatedDeliveryTime().hashCode());
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getPickupTime() == null) ? 0 : getPickupTime().hashCode());
        result = prime * result + ((getTimeoutTime() == null) ? 0 : getTimeoutTime().hashCode());
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
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", userId=").append(userId);
        sb.append(", address=").append(address);
        sb.append(", courierId=").append(courierId);
        sb.append(", platform=").append(platform);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", cabinetNumber=").append(cabinetNumber);
        sb.append(", estimatedDeliveryTime=").append(estimatedDeliveryTime);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append(", timeoutTime=").append(timeoutTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}