package com.qcx.property.domain.vo.ExpressOrder;

import com.qcx.property.domain.entity.Announcement;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.vo.announcement.AnnouncementVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 快递订单 vo （管理员）
 * @author: yannqing
 * @create: 2025-02-17 19:17
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class ExpressOrderAdminVo {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 取件用户id
     */
    private UserVo pickupUser;

    /**
     * 订单用户id
     */
    private UserVo userId;

    /**
     * 取件码
     */
    private String pickupCode;

    /**
     * 快递单号
     */
    private String trackingNumber;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 状态（待处理/已取件/已存放/已完成/已取消/异常）
     */
    private String status;

    public static ExpressOrderAdminVo expressOrderToVo(ExpressOrder expressOrder) {
        if (expressOrder == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        ExpressOrderAdminVo expressOrderAdminVo = new ExpressOrderAdminVo();
        BeanUtils.copyProperties(expressOrder, expressOrderAdminVo);
        return expressOrderAdminVo;
    }
}
