package com.qcx.property.domain.dto.takeouts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.entity.CostType;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 更新外卖记录 dto （管理员）
 * @author: yannqing
 * @create: 2025-02-17 09:58
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateTakeoutsDto", description = "修改外卖记录对象（管理员）")
public class UpdateTakeoutsDto {
    /**
     * 主键ID
     */
    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 外卖订单号
     */
    @Schema(description = "外卖订单号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String orderNumber;

    /**
     * 收件人id
     */
    @Schema(description = "收件人id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 外卖地址
     */
    @Schema(description = "外卖地址", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;

    /**
     * 快递员id
     */
    @Schema(description = "快递员id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer courierId;

    /**
     * 外卖平台
     */
    @Schema(description = "外卖平台", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer platform;

    /**
     * 状态(1:已收取 2:已通知 3:已领取 4:已超时)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String remark;

    /**
     * 柜号
     */
    @Schema(description = "柜号 id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer cabinetNumber;

    /**
     * 预计送达时间
     */
    @Schema(description = "预计送达时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date estimatedDeliveryTime;

    /**
     * 送达通知时间
     */
    @Schema(description = "送达通知时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date notifyTime;

    /**
     * 领取时间
     */
    @Schema(description = "领取时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date pickupTime;

    /**
     * 外卖超时时间
     */
    @Schema(description = "外卖超时时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date timeoutTime;

    public static TakeoutPickupRecord objToTakeouts(UpdateTakeoutsDto updateTakeoutsDto) {
        if (updateTakeoutsDto == null) {
            return null;
        }

        TakeoutPickupRecord takeoutPickupRecord = new TakeoutPickupRecord();
        BeanUtils.copyProperties(updateTakeoutsDto, takeoutPickupRecord);
        return takeoutPickupRecord;
    }
}
