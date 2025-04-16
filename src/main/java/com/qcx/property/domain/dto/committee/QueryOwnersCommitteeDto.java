package com.qcx.property.domain.dto.committee;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询业主委员会DTO
 * @author: yannqing
 * @create: 2025-04-16 11:00
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryOwnersCommitteeDto", description = "查询业主委员会请求参数")
public class QueryOwnersCommitteeDto extends PageRequest implements Serializable {
    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 委员会名称
     */
    @Schema(description = "委员会名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态(0禁用,1启用)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 委员会描述
     */
    @Schema(description = "委员会描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 成立时间范围-开始
     */
    @Schema(description = "成立时间范围-开始", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date establishmentDateStart;

    /**
     * 成立时间范围-结束
     */
    @Schema(description = "成立时间范围-结束", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date establishmentDateEnd;

    /**
     * 任期开始时间
     */
    @Schema(description = "任期开始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date termStartDate;

    /**
     * 任期结束时间
     */
    @Schema(description = "任期结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date termEndDate;

    @Serial
    private static final long serialVersionUID = 1L;
} 