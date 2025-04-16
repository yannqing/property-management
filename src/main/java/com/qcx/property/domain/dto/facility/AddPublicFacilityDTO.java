package com.qcx.property.domain.dto.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "添加公共设施DTO")
public class AddPublicFacilityDTO {
    
    @Schema(description = "设施名称", required = true)
    private String name;
    
    @Schema(description = "设施类型(1:健身设施,2:娱乐设施,3:会议室,4:活动室,5:其他)", required = true)
    private Integer type;
    
    @Schema(description = "设施位置", required = true)
    private String location;
    
    @Schema(description = "可容纳人数", required = true)
    private Integer capacity;
    
    @Schema(description = "状态(0禁用,1启用)", required = true)
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