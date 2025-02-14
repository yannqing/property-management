package com.qcx.property.domain.dto.ExpressOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.entity.Message;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 新增快递订单 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddExpressOrderDto", description = "新增快递订单请求参数")
public class AddExpressOrderDto implements Serializable {
    /**
     * 取件用户id
     */
    @Schema(description = "取件用户id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer pickupUser;

    /**
     * 订单用户id
     */
    @Schema(description = "订单用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private String userId;

    /**
     * 取件码
     */
    @Schema(description = "取件码", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String pickupCode;

    /**
     * 快递单号
     */
    @Schema(description = "快递单号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String trackingNumber;

    /**
     * 快递公司
     */
    @Schema(description = "快递公司", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer expressCompany;

    /**
     * 状态（待处理/已取件/已存放/已完成/已取消/异常）
     */
    @Schema(description = "发送者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    public static ExpressOrder objToExpressOrder(AddExpressOrderDto addExpressOrderDto) {
        if (addExpressOrderDto == null) {
            return null;
        }

        ExpressOrder expressOrder = new ExpressOrder();
        BeanUtils.copyProperties(addExpressOrderDto, expressOrder);
        return expressOrder;
    }
}
