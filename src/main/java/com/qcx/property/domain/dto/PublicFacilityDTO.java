package com.qcx.property.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "公共设施DTO")
public class PublicFacilityDTO {
    
    @Schema(description = "设施ID")
    private Long id;
    
    @Schema(description = "设施名称", required = true)
    private String name;
    
    @Schema(description = "设施类型", required = true)
    private String type;
    
    @Schema(description = "设施位置", required = true)
    private String location;
    
    @Schema(description = "设施容量", required = true)
    private Integer capacity;
    
    @Schema(description = "设施状态：0-不可用，1-可用", required = true)
    private Integer status;
    
    @Schema(description = "开放时间", required = true)
    private LocalTime openTime;
    
    @Schema(description = "关闭时间", required = true)
    private LocalTime closeTime;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "使用规则")
    private String usageRule;
} 