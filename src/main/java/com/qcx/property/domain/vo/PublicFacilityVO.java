package com.qcx.property.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Data
@Schema(description = "公共设施VO")
public class PublicFacilityVO {
    
    @Schema(description = "设施ID")
    private Long id;
    
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施类型(1:健身设施,2:娱乐设施,3:会议室,4:活动室,5:其他)")
    private String type;
    
    @Schema(description = "设施位置")
    private String location;
    
    @Schema(description = "设施容量")
    private Integer capacity;
    
    @Schema(description = "设施状态：0-不可用，1-可用")
    private Integer status;
    
    @Schema(description = "开放时间")
    private LocalTime openTime;
    
    @Schema(description = "关闭时间")
    private LocalTime closeTime;
    
    @Schema(description = "设施描述")
    private String description;
    
    @Schema(description = "使用规则")
    private String usageRule;
    
    @Schema(description = "当前可预约状态：true-可预约，false-不可预约")
    private Boolean available;
} 