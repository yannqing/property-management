package com.qcx.property.domain.dto.ExpressOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 更新快递订单 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateExpressOrderDto", description = "修改快递订单对象")
public class UpdateExpressOrderDto {
    /**
     * 订单id
     */
    @Schema(description = "订单id", requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static ExpressOrder objToExpressOrder(UpdateExpressOrderDto updateExpressOrderDto) {
        if (updateExpressOrderDto == null) {
            return null;
        }

        ExpressOrder expressOrder = new ExpressOrder();
        BeanUtils.copyProperties(updateExpressOrderDto, expressOrder);
        return expressOrder;
    }
}
