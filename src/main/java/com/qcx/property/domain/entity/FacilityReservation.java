package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName facility_reservation
 */
@TableName(value ="facility_reservation")
@Data
public class FacilityReservation implements Serializable {
    /**
     * 预约id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设施id
     */
    @TableField(value = "facilityId")
    private Integer facilityId;

    /**
     * 用户id
     */
    @TableField(value = "userId")
    private Integer userId;

    /**
     * 开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 使用人数
     */
    @TableField(value = "persons")
    private Integer persons;

    /**
     * 使用目的
     */
    @TableField(value = "purpose")
    private String purpose;

    /**
     * 状态(0待审核,1已通过,2已拒绝,3已取消,4已完成)
     */
    @TableField(value = "status")
    private Integer status;

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

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

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
        FacilityReservation other = (FacilityReservation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFacilityId() == null ? other.getFacilityId() == null : this.getFacilityId().equals(other.getFacilityId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getPersons() == null ? other.getPersons() == null : this.getPersons().equals(other.getPersons()))
            && (this.getPurpose() == null ? other.getPurpose() == null : this.getPurpose().equals(other.getPurpose()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFacilityId() == null) ? 0 : getFacilityId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getPersons() == null) ? 0 : getPersons().hashCode());
        result = prime * result + ((getPurpose() == null) ? 0 : getPurpose().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", facilityId=").append(facilityId);
        sb.append(", userId=").append(userId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", persons=").append(persons);
        sb.append(", purpose=").append(purpose);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
} 