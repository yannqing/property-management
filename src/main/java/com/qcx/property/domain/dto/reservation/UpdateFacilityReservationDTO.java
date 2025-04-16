package com.qcx.property.domain.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Schema(description = "更新设施预约DTO")
public class UpdateFacilityReservationDTO {
    
    @Schema(description = "预约id", required = true)
    private Integer id;
    
    @Schema(description = "状态(0待审核,1已通过,2已拒绝,3已取消,4已完成)")
    private Integer status;
    
    @Schema(description = "开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    
    @Schema(description = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    
    @Schema(description = "使用人数")
    private Integer persons;
    
    @Schema(description = "使用目的")
    private String purpose;
    
    @Schema(description = "备注")
    private String remarks;
} 