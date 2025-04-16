package com.qcx.property.domain.dto.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "更新公共设施DTO")
public class UpdatePublicFacilityDTO {
    
    @Schema(description = "设施ID", required = true)
    private Integer id;
    
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施类型(1:健身设施,2:娱乐设施,3:会议室,4:活动室,5:其他)")
    private Integer type;
    
    @Schema(description = "设施位置")
    private String location;
    
    @Schema(description = "可容纳人数")
    private Integer capacity;
    
    @Schema(description = "状态(0禁用,1启用)")
    private Integer status;
    
    @Schema(description = "开放时间")
    private LocalTime openTime;
    
    @Schema(description = "关闭时间")
    private LocalTime closeTime;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "使用规则")
    private String usageRule;
} 