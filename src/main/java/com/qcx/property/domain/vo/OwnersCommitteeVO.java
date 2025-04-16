package com.qcx.property.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @description: 业主委员会VO
 * @author: yannqing
 * @create: 2025-04-16 12:40
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "OwnersCommitteeVO", description = "业主委员会信息")
public class OwnersCommitteeVO implements Serializable {
    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID")
    private Integer id;

    /**
     * 委员会名称
     */
    @Schema(description = "委员会名称")
    private String name;

    /**
     * 委员会描述
     */
    @Schema(description = "委员会描述")
    private String description;

    /**
     * 成立日期
     */
    @Schema(description = "成立日期")
    private Date establishmentDate;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态(0禁用,1启用)")
    private Integer status;

    /**
     * 状态描述
     */
    @Schema(description = "状态描述")
    private String statusDesc;

    /**
     * 任期开始日期
     */
    @Schema(description = "任期开始日期")
    private Date termStartDate;

    /**
     * 任期结束日期
     */
    @Schema(description = "任期结束日期")
    private Date termEndDate;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 委员会成员列表
     */
    @Schema(description = "委员会成员列表")
    private List<CommitteeMemberVO> members;

    /**
     * 委员会会议数量
     */
    @Schema(description = "委员会会议数量")
    private Integer meetingCount;

    @Serial
    private static final long serialVersionUID = 1L;
}