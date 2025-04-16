package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName owners_committee
 */
@TableName(value ="owners_committee")
@Data
public class OwnersCommittee implements Serializable {
    /**
     * 委员会ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 委员会名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 委员会描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 成立日期
     */
    @TableField(value = "establishment_date")
    private Date establishmentDate;

    /**
     * 状态(0禁用,1启用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 任期开始日期
     */
    @TableField(value = "term_start_date")
    private Date termStartDate;

    /**
     * 任期结束日期
     */
    @TableField(value = "term_end_date")
    private Date termEndDate;

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
     * 逻辑删除(0-未删除,1-已删除)
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
        OwnersCommittee other = (OwnersCommittee) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getEstablishmentDate() == null ? other.getEstablishmentDate() == null : this.getEstablishmentDate().equals(other.getEstablishmentDate()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTermStartDate() == null ? other.getTermStartDate() == null : this.getTermStartDate().equals(other.getTermStartDate()))
            && (this.getTermEndDate() == null ? other.getTermEndDate() == null : this.getTermEndDate().equals(other.getTermEndDate()))
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
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getEstablishmentDate() == null) ? 0 : getEstablishmentDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTermStartDate() == null) ? 0 : getTermStartDate().hashCode());
        result = prime * result + ((getTermEndDate() == null) ? 0 : getTermEndDate().hashCode());
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
        sb.append(", description=").append(description);
        sb.append(", establishmentDate=").append(establishmentDate);
        sb.append(", status=").append(status);
        sb.append(", termStartDate=").append(termStartDate);
        sb.append(", termEndDate=").append(termEndDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
} 