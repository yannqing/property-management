package com.qcx.property.domain.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Schema(description = "添加设施预约DTO")
public class AddFacilityReservationDTO {
    
    @Schema(description = "设施id", required = true)
    private Integer facilityId;
    
    @Schema(description = "用户id", required = true)
    private Integer userId;
    
    @Schema(description = "开始时间", required = true, format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    
    @Schema(description = "结束时间", required = true, format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    
    @Schema(description = "使用人数", required = true)
    private Integer persons;
    
    @Schema(description = "使用目的")
    private String purpose;
    
    @Schema(description = "备注")
    private String remarks;
} 