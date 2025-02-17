package com.qcx.property.domain.vo.communityActivity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 活动报名 vo
 * @author: yannqing
 * @create: 2025-02-17 18:46
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class ActivityRegistrationVo {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 活动ID
     */
    private CommunityActivityVo activity;

    /**
     * 用户id
     */
    private UserVo user;

    /**
     * 状态(1:已报名 2:已签到 3:已取消)
     */
    private String status;

    /**
     * 报名时间
     */
    private Date registerTime;

    /**
     * 签到时间
     */
    private Date checkInTime;

    /**
     * 参与人数（一人可带家属）
     */
    private Integer participantCount;

    public static ActivityRegistrationVo activityRegistrationToVo(ActivityRegistration activityRegistration) {
        if (activityRegistration == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        ActivityRegistrationVo activityRegistrationVo = new ActivityRegistrationVo();
        BeanUtils.copyProperties(activityRegistration, activityRegistrationVo);
        return activityRegistrationVo;
    }
}
