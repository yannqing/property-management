package com.qcx.property.domain.dto.ExpressOrder;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询快递订单Dto
 * @author: yannqing
 * @create: 2025-02-14 17:15
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryExpressOrderDto", description = "查询所有快递订单请求参数")
public class QueryExpressOrderDto extends PageRequest implements Serializable {
    /**
     * 订单id
     */
    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 取件用户id
     */
    @Schema(description = "取件用户id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pickupUser;

    /**
     * 订单用户id
     */
    @Schema(description = "订单用户id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String userId;

    /**
     * 取件码
     */
    @Schema(description = "取件码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String pickupCode;

    /**
     * 快递单号
     */
    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String trackingNumber;

    /**
     * 快递公司
     */
    @Schema(description = "快递公司", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer expressCompany;

    /**
     * 状态（待处理/已取件/已存放/已完成/已取消/异常）
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date updateTime;
}
