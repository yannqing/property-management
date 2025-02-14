package com.qcx.property.domain.vo.ExpressOrder;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.entity.Message;
import com.qcx.property.domain.vo.user.UserVo;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 封装消息通知vo（普通用户）
 * @author: yannqing
 * @create: 2025-02-14 17:38
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class ExpressOrderVo implements Serializable {
    /**
     * 取件用户
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

    @Serial
    private static final long serialVersionUID = 1L;

    public static ExpressOrderVo objToVo(ExpressOrder expressOrder) {
        if (expressOrder == null) {
            return null;
        }

        ExpressOrderVo expressOrderVo = new ExpressOrderVo();
        BeanUtils.copyProperties(expressOrder, expressOrderVo);
        return expressOrderVo;
    }
}
