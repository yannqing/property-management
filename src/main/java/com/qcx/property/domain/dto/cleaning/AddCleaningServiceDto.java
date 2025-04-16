package com.qcx.property.domain.dto.cleaning;

import com.qcx.property.domain.entity.CleaningService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 新增保洁服务 dto
 * @author: yannqing
 * @create: 2025-02-09 10:25
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddCleaningServiceDto", description = "新增保洁服务请求参数")
public class AddCleaningServiceDto implements Serializable {
    /**
     * 保洁服务名称
     */
    @Schema(description = "保洁服务名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    /**
     * 保洁类型id
     */
    @Schema(description = "保洁类型id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer type;

    /**
     * 服务描述
     */
    @Schema(description = "服务描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 服务价格
     */
    @Schema(description = "服务价格", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    /**
     * 服务时长(分钟)
     */
    @Schema(description = "服务时长(分钟)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer duration;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    @Serial
    private static final long serialVersionUID = 1L;

    public static CleaningService objToCleaningService(AddCleaningServiceDto addCleaningServiceDto) {
        if (addCleaningServiceDto == null) {
            return null;
        }

        CleaningService cleaningService = new CleaningService();
        BeanUtils.copyProperties(addCleaningServiceDto, cleaningService);
        return cleaningService;
    }
} 