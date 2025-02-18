package com.qcx.property.domain.model;

import com.qcx.property.common.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 分页请求
 *
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    @Schema(description = "当前页号（默认：1）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int current = 1;

    /**
     * 页面大小
     */
    @Schema(description = "页面大小（默认：10）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int pageSize = 10;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    @Schema(description = "排序顺序（默认：升序）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}