package com.qcx.property.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName committee_meeting
 */
@TableName(value ="committee_meeting")
@Data
public class CommitteeMeeting implements Serializable {
    /**
     * 会议ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 委员会ID
     */
    @TableField(value = "committee_id")
    private Integer committeeId;

    /**
     * 会议标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 会议日期时间
     */
    @TableField(value = "meeting_date")
    private Date meetingDate;

    /**
     * 会议地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 会议内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 组织者ID
     */
    @TableField(value = "organizer_id")
    private Integer organizerId;

    /**
     * 状态(0筹备中,1已召开,2已取消)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 参会人员
     */
    @TableField(value = "attendees")
    private String attendees;

    /**
     * 会议纪要
     */
    @TableField(value = "minutes")
    private String minutes;

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
        CommitteeMeeting other = (CommitteeMeeting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCommitteeId() == null ? other.getCommitteeId() == null : this.getCommitteeId().equals(other.getCommitteeId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getMeetingDate() == null ? other.getMeetingDate() == null : this.getMeetingDate().equals(other.getMeetingDate()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getOrganizerId() == null ? other.getOrganizerId() == null : this.getOrganizerId().equals(other.getOrganizerId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAttendees() == null ? other.getAttendees() == null : this.getAttendees().equals(other.getAttendees()))
            && (this.getMinutes() == null ? other.getMinutes() == null : this.getMinutes().equals(other.getMinutes()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCommitteeId() == null) ? 0 : getCommitteeId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getMeetingDate() == null) ? 0 : getMeetingDate().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getOrganizerId() == null) ? 0 : getOrganizerId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAttendees() == null) ? 0 : getAttendees().hashCode());
        result = prime * result + ((getMinutes() == null) ? 0 : getMinutes().hashCode());
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
        sb.append(", committeeId=").append(committeeId);
        sb.append(", title=").append(title);
        sb.append(", meetingDate=").append(meetingDate);
        sb.append(", location=").append(location);
        sb.append(", content=").append(content);
        sb.append(", organizerId=").append(organizerId);
        sb.append(", status=").append(status);
        sb.append(", attendees=").append(attendees);
        sb.append(", minutes=").append(minutes);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
} 