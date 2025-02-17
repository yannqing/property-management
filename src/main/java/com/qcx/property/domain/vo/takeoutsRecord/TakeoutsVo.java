package com.qcx.property.domain.vo.takeoutsRecord;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.entity.Announcement;
import com.qcx.property.domain.entity.Cabinet;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.qcx.property.domain.vo.announcement.AnnouncementVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 外卖订单信息 vo
 * @author: yannqing
 * @create: 2025-02-17 17:40
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class TakeoutsVo {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 外卖订单号
     */
    private String orderNumber;

    /**
     * 收件人
     */
    private UserVo receiveUser;

    /**
     * 外卖地址
     */
    private String address;

    /**
     * 快递员
     */
    private UserVo courier;

    /**
     * 外卖平台
     */
    private String platform;

    /**
     * 状态(已下单/骑手已接单/派送中/已送达/已完成/已取消/异常)
     */
    private String status;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 柜号
     */
    private Cabinet cabinet;

    /**
     * 预计送达时间
     */
    private Date estimatedDeliveryTime;

    /**
     * 送达通知时间
     */
    private Date notifyTime;

    /**
     * 领取时间
     */
    private Date pickupTime;

    /**
     * 外卖超时时间
     */
    private Date timeoutTime;


    public static TakeoutsVo takeoutsPickupRecordToVo(TakeoutPickupRecord takeoutPickupRecord) {
        if (takeoutPickupRecord == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        TakeoutsVo takeoutsVo = new TakeoutsVo();
        BeanUtils.copyProperties(takeoutPickupRecord, takeoutsVo);
        return takeoutsVo;
    }
}
