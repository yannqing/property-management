package com.qcx.property.domain.dto.committee;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询委员会成员DTO
 * @author: yannqing
 * @create: 2025-04-16 11:30
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCommitteeMemberDto", description = "查询委员会成员请求参数")
public class QueryCommitteeMemberDto extends PageRequest implements Serializable {
    /**
     * 成员ID
     */
    @Schema(description = "成员ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer committeeId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 职位(主任,副主任,委员)
     */
    @Schema(description = "职位(主任,副主任,委员)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String position;

    /**
     * 加入日期范围-开始
     */
    @Schema(description = "加入日期范围-开始", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date joinDateStart;

    /**
     * 加入日期范围-结束
     */
    @Schema(description = "加入日期范围-结束", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date joinDateEnd;

    /**
     * 状态(0离任,1在任)
     */
    @Schema(description = "状态(0离任,1在任)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 职责描述
     */
    @Schema(description = "职责描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String responsibilities;

    @Serial
    private static final long serialVersionUID = 1L;
} 