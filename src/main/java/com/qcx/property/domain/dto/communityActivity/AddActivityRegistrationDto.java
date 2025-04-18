package com.qcx.property.domain.dto.communityActivity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.entity.ActivityRegistration;
import com.qcx.property.domain.entity.CommunityActivity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 新增活动报名 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddActivityRegistrationDto", description = "新增活动报名请求参数")
public class AddActivityRegistrationDto implements Serializable {
    /**
     * 活动ID
     */
    @Schema(description = "活动ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer activityId;

    /**
     * 用户id
     */
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer userId;

    /**
     * 状态(1:已报名 2:已签到 3:已取消)
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 报名时间
     */
    @Schema(description = "报名时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date registerTime;

    /**
     * 签到时间
     */
    @Schema(description = "签到时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date checkInTime;

    /**
     * 参与人数（一人可带家属）
     */
    @Schema(description = "参与人数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer participantCount;

    @Serial
    private static final long serialVersionUID = 1L;

    public static ActivityRegistration objToActivityRegistration(AddActivityRegistrationDto addActivityRegistrationDto) {
        if (addActivityRegistrationDto == null) {
            return null;
        }

        ActivityRegistration activityRegistration = new ActivityRegistration();
        BeanUtils.copyProperties(addActivityRegistrationDto, activityRegistration);
        return activityRegistration;
    }
}
