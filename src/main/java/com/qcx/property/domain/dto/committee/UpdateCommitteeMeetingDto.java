package com.qcx.property.domain.dto.committee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qcx.property.domain.entity.CommitteeMeeting;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 更新委员会会议DTO
 * @author: yannqing
 * @create: 2025-04-16 12:20
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCommitteeMeetingDto", description = "更新委员会会议请求参数")
public class UpdateCommitteeMeetingDto implements Serializable {
    /**
     * 会议ID
     */
    @Schema(description = "会议ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer committeeId;

    /**
     * 会议标题
     */
    @Schema(description = "会议标题", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String title;

    /**
     * 会议日期时间
     */
    @Schema(description = "会议日期时间(格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingDate;

    /**
     * 会议地点
     */
    @Schema(description = "会议地点", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String location;

    /**
     * 会议内容
     */
    @Schema(description = "会议内容", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String content;

    /**
     * 组织者ID
     */
    @Schema(description = "组织者ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer organizerId;

    /**
     * 状态(0筹备中,1已召开,2已取消)
     */
    @Schema(description = "状态(0筹备中,1已召开,2已取消)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 参会人员
     */
    @Schema(description = "参会人员", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String attendees;

    /**
     * 会议纪要
     */
    @Schema(description = "会议纪要", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String minutes;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 将DTO转换为实体
     * @param updateCommitteeMeetingDto DTO对象
     * @return 实体对象
     */
    public static CommitteeMeeting toEntity(UpdateCommitteeMeetingDto updateCommitteeMeetingDto) {
        if (updateCommitteeMeetingDto == null) {
            return null;
        }
        CommitteeMeeting committeeMeeting = new CommitteeMeeting();
        BeanUtils.copyProperties(updateCommitteeMeetingDto, committeeMeeting);
        return committeeMeeting;
    }
} 