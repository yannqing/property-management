package com.qcx.property.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 委员会成员VO
 * @author: yannqing
 * @create: 2025-04-16 12:30
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "CommitteeMemberVO", description = "委员会成员信息")
public class CommitteeMemberVO implements Serializable {
    /**
     * 成员ID
     */
    @Schema(description = "成员ID")
    private Integer id;

    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID")
    private Integer committeeId;

    /**
     * 委员会名称
     */
    @Schema(description = "委员会名称")
    private String committeeName;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Integer userId;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String userName;

    /**
     * 用户手机号
     */
    @Schema(description = "用户手机号")
    private String userPhone;

    /**
     * 职位(主任,副主任,委员)
     */
    @Schema(description = "职位(主任,副主任,委员)")
    private String position;

    /**
     * 加入日期
     */
    @Schema(description = "加入日期")
    private Date joinDate;

    /**
     * 状态(0离任,1在任)
     */
    @Schema(description = "状态(0离任,1在任)")
    private Integer status;

    /**
     * 状态描述
     */
    @Schema(description = "状态描述")
    private String statusDesc;

    /**
     * 职责描述
     */
    @Schema(description = "职责描述")
    private String responsibilities;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    @Serial
    private static final long serialVersionUID = 1L;
} 