package com.qcx.property.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 委员会会议VO
 * @author: yannqing
 * @create: 2025-04-16 12:50
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "CommitteeMeetingVO", description = "委员会会议信息")
public class CommitteeMeetingVO implements Serializable {
    /**
     * 会议ID
     */
    @Schema(description = "会议ID")
    private Integer id;

    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID")
    private Integer committeeId;

    /**
     * 委员会名称
     */
    @Schema(description = "委员会名称")
    private String committeeName;

    /**
     * 会议标题
     */
    @Schema(description = "会议标题")
    private String title;

    /**
     * 会议日期时间
     */
    @Schema(description = "会议日期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingDate;

    /**
     * 会议地点
     */
    @Schema(description = "会议地点")
    private String location;

    /**
     * 会议内容
     */
    @Schema(description = "会议内容")
    private String content;

    /**
     * 组织者ID
     */
    @Schema(description = "组织者ID")
    private Integer organizerId;

    /**
     * 组织者姓名
     */
    @Schema(description = "组织者姓名")
    private String organizerName;

    /**
     * 状态(0筹备中,1已召开,2已取消)
     */
    @Schema(description = "状态(0筹备中,1已召开,2已取消)")
    private Integer status;

    /**
     * 状态描述
     */
    @Schema(description = "状态描述")
    private String statusDesc;

    /**
     * 参会人员
     */
    @Schema(description = "参会人员")
    private String attendees;

    /**
     * 会议纪要
     */
    @Schema(description = "会议纪要")
    private String minutes;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
} 