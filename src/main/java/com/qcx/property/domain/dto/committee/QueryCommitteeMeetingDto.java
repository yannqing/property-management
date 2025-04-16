package com.qcx.property.domain.dto.committee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询委员会会议DTO
 * @author: yannqing
 * @create: 2025-04-16 12:00
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCommitteeMeetingDto", description = "查询委员会会议请求参数")
public class QueryCommitteeMeetingDto extends PageRequest implements Serializable {
    /**
     * 会议ID
     */
    @Schema(description = "会议ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
     * 会议日期时间范围-开始
     */
    @Schema(description = "会议日期时间范围-开始(格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingDateStart;

    /**
     * 会议日期时间范围-结束
     */
    @Schema(description = "会议日期时间范围-结束(格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingDateEnd;

    /**
     * 会议地点
     */
    @Schema(description = "会议地点", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String location;

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

    @Serial
    private static final long serialVersionUID = 1L;
} 