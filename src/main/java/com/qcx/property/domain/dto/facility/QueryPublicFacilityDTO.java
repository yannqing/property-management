package com.qcx.property.domain.dto.facility;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "查询公共设施DTO")
public class QueryPublicFacilityDTO {
    
    @Schema(description = "设施名称")
    private String name;
    
    @Schema(description = "设施类型")
    private Integer type;
    
    @Schema(description = "设施位置")
    private String location;
    
    @Schema(description = "设施状态：0-不可用，1-可用")
    private Integer status;
    
    @Schema(description = "页码", required = true)
    private Integer pageNum = 1;
    
    @Schema(description = "每页数量", required = true)
    private Integer pageSize = 10;
} 