package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName public_facility
 */
@TableName(value ="public_facility")
@Data
public class PublicFacility implements Serializable {
    /**
     * 设施id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设施名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 设施类型(1:健身设施,2:娱乐设施,3:会议室,4:活动室,5:其他)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 设施位置
     */
    @TableField(value = "location")
    private String location;

    /**
     * 可容纳人数
     */
    @TableField(value = "capacity")
    private Integer capacity;

    /**
     * 状态(0禁用,1启用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 开放时间
     */
    @TableField(value = "openTime")
    private LocalTime openTime;

    /**
     * 关闭时间
     */
    @TableField(value = "closeTime")
    private LocalTime closeTime;

    /**
     * 设施描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 使用规则
     */
    @TableField(value = "usageRule")
    private String usageRule;

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
        PublicFacility other = (PublicFacility) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCapacity() == null ? other.getCapacity() == null : this.getCapacity().equals(other.getCapacity()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOpenTime() == null ? other.getOpenTime() == null : this.getOpenTime().equals(other.getOpenTime()))
            && (this.getCloseTime() == null ? other.getCloseTime() == null : this.getCloseTime().equals(other.getCloseTime()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getUsageRule() == null ? other.getUsageRule() == null : this.getUsageRule().equals(other.getUsageRule()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCapacity() == null) ? 0 : getCapacity().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOpenTime() == null) ? 0 : getOpenTime().hashCode());
        result = prime * result + ((getCloseTime() == null) ? 0 : getCloseTime().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getUsageRule() == null) ? 0 : getUsageRule().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", location=").append(location);
        sb.append(", capacity=").append(capacity);
        sb.append(", status=").append(status);
        sb.append(", openTime=").append(openTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", description=").append(description);
        sb.append(", usageRule=").append(usageRule);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
} 