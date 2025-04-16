package com.qcx.property.domain.vo.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "设施预约VO")
public class FacilityReservationVO {
    
    @Schema(description = "预约id")
    private Integer id;
    
    @Schema(description = "设施id")
    private Integer facilityId;
    
    @Schema(description = "设施名称")
    private String facilityName;
    
    @Schema(description = "设施类型")
    private Integer facilityType;
    
    @Schema(description = "设施类型名称")
    private String facilityTypeName;
    
    @Schema(description = "设施位置")
    private String facilityLocation;
    
    @Schema(description = "用户id")
    private Integer userId;
    
    @Schema(description = "用户名")
    private String userName;
    
    @Schema(description = "开始时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    
    @Schema(description = "结束时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    
    @Schema(description = "使用人数")
    private Integer persons;
    
    @Schema(description = "使用目的")
    private String purpose;
    
    @Schema(description = "状态(0待审核,1已通过,2已拒绝,3已取消,4已完成)")
    private Integer status;
    
    @Schema(description = "状态描述")
    private String statusDesc;
    
    @Schema(description = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    
    @Schema(description = "更新时间", format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    
    @Schema(description = "备注")
    private String remarks;
} 