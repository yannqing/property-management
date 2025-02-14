package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社区活动表
 * @TableName community_activity
 */
@TableName(value ="community_activity")
@Data
public class CommunityActivity implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 活动描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 活动类型(1:文化活动 2:体育活动 3:志愿服务 4:节日庆祝 5:教育讲座)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 活动开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 活动地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 最大参与人数
     */
    @TableField(value = "maxParticipants")
    private Integer maxParticipants;

    /**
     * 当前参与人数
     */
    @TableField(value = "currentParticipants")
    private Integer currentParticipants;

    /**
     * 组织者id
     */
    @TableField(value = "organizer")
    private Integer organizer;

    /**
     * 联系电话
     */
    @TableField(value = "contactPhone")
    private String contactPhone;

    /**
     * 活动状态(0:草稿 1:报名中 2:进行中 3:已结束 4:已取消)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 活动封面图片
     */
    @TableField(value = "coverImage")
    private String coverImage;

    /**
     * 报名截止时间
     */
    @TableField(value = "signUpDeadline")
    private Date signUpDeadline;

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
        CommunityActivity other = (CommunityActivity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getMaxParticipants() == null ? other.getMaxParticipants() == null : this.getMaxParticipants().equals(other.getMaxParticipants()))
            && (this.getCurrentParticipants() == null ? other.getCurrentParticipants() == null : this.getCurrentParticipants().equals(other.getCurrentParticipants()))
            && (this.getOrganizer() == null ? other.getOrganizer() == null : this.getOrganizer().equals(other.getOrganizer()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCoverImage() == null ? other.getCoverImage() == null : this.getCoverImage().equals(other.getCoverImage()))
            && (this.getSignUpDeadline() == null ? other.getSignUpDeadline() == null : this.getSignUpDeadline().equals(other.getSignUpDeadline()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getMaxParticipants() == null) ? 0 : getMaxParticipants().hashCode());
        result = prime * result + ((getCurrentParticipants() == null) ? 0 : getCurrentParticipants().hashCode());
        result = prime * result + ((getOrganizer() == null) ? 0 : getOrganizer().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCoverImage() == null) ? 0 : getCoverImage().hashCode());
        result = prime * result + ((getSignUpDeadline() == null) ? 0 : getSignUpDeadline().hashCode());
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
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", location=").append(location);
        sb.append(", maxParticipants=").append(maxParticipants);
        sb.append(", currentParticipants=").append(currentParticipants);
        sb.append(", organizer=").append(organizer);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", status=").append(status);
        sb.append(", coverImage=").append(coverImage);
        sb.append(", signUpDeadline=").append(signUpDeadline);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}