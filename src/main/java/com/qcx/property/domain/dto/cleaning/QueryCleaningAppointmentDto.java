package com.qcx.property.domain.dto.cleaning;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询保洁预约 dto
 * @author: yannqing
 * @create: 2025-02-09 10:35
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryCleaningAppointmentDto", description = "查询保洁预约请求参数")
public class QueryCleaningAppointmentDto extends PageRequest implements Serializable {
    /**
     * 预约id
     */
    @Schema(description = "预约id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer serviceId;

    /**
     * 用户id
     */
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 预约起始时间
     */
    @Schema(description = "预约起始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date startTime;

    /**
     * 预约结束时间
     */
    @Schema(description = "预约结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date endTime;

    /**
     * 状态(0待确认,1已确认,2已完成,3已取消,-1拒绝)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 支付状态(0未支付,1已支付)
     */
    @Schema(description = "支付状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer paymentStatus;

    @Serial
    private static final long serialVersionUID = 1L;
} 