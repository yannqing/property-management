package com.qcx.property.domain.dto.communityActivity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.entity.CostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 更新社区活动 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCommunityActivityDto", description = "修改社区活动对象")
public class UpdateCommunityActivityDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 活动标题
     */
    @Schema(description = "活动标题", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String title;

    /**
     * 活动描述
     */
    @Schema(description = "活动描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    /**
     * 活动类型(1:文化活动 2:体育活动 3:志愿服务 4:节日庆祝 5:教育讲座)
     */
    @Schema(description = "活动类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 活动开始时间
     */
    @Schema(description = "活动开始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date startTime;

    /**
     * 活动结束时间
     */
    @Schema(description = "活动结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date endTime;

    /**
     * 活动地点
     */
    @Schema(description = "活动地点", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String location;

    /**
     * 最大参与人数
     */
    @Schema(description = "最大参与人数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer maxParticipants;

    /**
     * 当前参与人数
     */
    @Schema(description = "当前参与人数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer currentParticipants;

    /**
     * 组织者id
     */
    @Schema(description = "组织者id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer organizer;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String contactPhone;

    /**
     * 活动状态(0:草稿 1:报名中 2:进行中 3:已结束 4:已取消)
     */
    @Schema(description = "活动状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 活动封面图片
     */
    @Schema(description = "活动封面图片", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String coverImage;

    /**
     * 报名截止时间
     */
    @Schema(description = "报名截止时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Date signUpDeadline;


    public static CommunityActivity objToCommunityActivity(UpdateCommunityActivityDto updateCommunityActivityDto) {
        if (updateCommunityActivityDto == null) {
            return null;
        }

        CommunityActivity communityActivity = new CommunityActivity();
        BeanUtils.copyProperties(updateCommunityActivityDto, communityActivity);
        return communityActivity;
    }
}
