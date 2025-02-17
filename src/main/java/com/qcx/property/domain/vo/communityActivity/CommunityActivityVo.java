package com.qcx.property.domain.vo.communityActivity;

import com.qcx.property.domain.entity.Announcement;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.vo.announcement.AnnouncementVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 社区活动 vo
 * @author: yannqing
 * @create: 2025-02-17 18:24
 * @from: <更多资料：yannqing.com>
 **/
@Data
public class CommunityActivityVo {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动类型(1:文化活动 2:体育活动 3:志愿服务 4:节日庆祝 5:教育讲座)
     */
    private String type;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 最大参与人数
     */
    private Integer maxParticipants;

    /**
     * 当前参与人数
     */
    private Integer currentParticipants;

    /**
     * 组织者
     */
    private UserVo organizer;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 活动状态(0:草稿 1:报名中 2:进行中 3:已结束 4:已取消)
     */
    private String status;

    /**
     * 活动封面图片
     */
    private String coverImage;

    /**
     * 报名截止时间
     */
    private Date signUpDeadline;

    public static CommunityActivityVo communityActivityToVo(CommunityActivity communityActivity) {
        if (communityActivity == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        CommunityActivityVo communityActivityVo = new CommunityActivityVo();
        BeanUtils.copyProperties(communityActivity, communityActivityVo);
        return communityActivityVo;
    }
}
