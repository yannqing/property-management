package com.qcx.property.domain.dto.committee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qcx.property.domain.entity.OwnersCommittee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 添加业主委员会DTO
 * @author: yannqing
 * @create: 2025-04-16 11:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddOwnersCommitteeDto", description = "添加业主委员会请求参数")
public class AddOwnersCommitteeDto implements Serializable {
    /**
     * 委员会名称
     */
    @Schema(description = "委员会名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 委员会描述
     */
    @Schema(description = "委员会描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 成立日期
     */
    @Schema(description = "成立日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date establishmentDate;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态(0禁用,1启用)", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    /**
     * 任期开始日期
     */
    @Schema(description = "任期开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date termStartDate;

    /**
     * 任期结束日期
     */
    @Schema(description = "任期结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date termEndDate;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 将DTO转换为实体
     * @param addOwnersCommitteeDto DTO对象
     * @return 实体对象
     */
    public static OwnersCommittee toEntity(AddOwnersCommitteeDto addOwnersCommitteeDto) {
        if (addOwnersCommitteeDto == null) {
            return null;
        }
        OwnersCommittee ownersCommittee = new OwnersCommittee();
        BeanUtils.copyProperties(addOwnersCommitteeDto, ownersCommittee);
        return ownersCommittee;
    }
} 