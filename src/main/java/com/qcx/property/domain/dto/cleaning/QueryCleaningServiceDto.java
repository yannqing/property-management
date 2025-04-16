package com.qcx.property.domain.dto.cleaning;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 查询所有保洁服务 dto
 * @author: yannqing
 * @create: 2025-02-09 10:20
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCleaningServiceDto", description = "查询保洁服务请求参数")
public class QueryCleaningServiceDto extends PageRequest implements Serializable {
    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 保洁服务名称
     */
    @Schema(description = "保洁服务名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 保洁类型id
     */
    @Schema(description = "保洁类型id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 价格下限
     */
    @Schema(description = "价格下限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal minPrice;

    /**
     * 价格上限
     */
    @Schema(description = "价格上限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal maxPrice;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    @Serial
    private static final long serialVersionUID = 1L;
} 