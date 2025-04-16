package com.qcx.property.domain.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Schema(description = "查询设施预约DTO")
public class QueryFacilityReservationDTO {
    
    @Schema(description = "设施id")
    private Integer facilityId;
    
    @Schema(description = "用户id")
    private Integer userId;
    
    @Schema(description = "状态(0待审核,1已通过,2已拒绝,3已取消,4已完成)")
    private Integer status;
    
    @Schema(description = "开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startDate;
    
    @Schema(description = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endDate;
    
    @Schema(description = "页码", required = true)
    private Integer pageNum = 1;
    
    @Schema(description = "每页数量", required = true)
    private Integer pageSize = 10;
} 