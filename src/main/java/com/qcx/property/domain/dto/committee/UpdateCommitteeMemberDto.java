package com.qcx.property.domain.dto.committee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qcx.property.domain.entity.CommitteeMember;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 更新委员会成员DTO
 * @author: yannqing
 * @create: 2025-04-16 11:50
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCommitteeMemberDto", description = "更新委员会成员请求参数")
public class UpdateCommitteeMemberDto implements Serializable {
    /**
     * 成员ID
     */
    @Schema(description = "成员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 委员会ID
     */
    @Schema(description = "委员会ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer committeeId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer userId;

    /**
     * 职位(主任,副主任,委员)
     */
    @Schema(description = "职位(主任,副主任,委员)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String position;

    /**
     * 加入日期
     */
    @Schema(description = "加入日期", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date joinDate;

    /**
     * 状态(0离任,1在任)
     */
    @Schema(description = "状态(0离任,1在任)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 职责描述
     */
    @Schema(description = "职责描述", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String responsibilities;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 将DTO转换为实体
     * @param updateCommitteeMemberDto DTO对象
     * @return 实体对象
     */
    public static CommitteeMember toEntity(UpdateCommitteeMemberDto updateCommitteeMemberDto) {
        if (updateCommitteeMemberDto == null) {
            return null;
        }
        CommitteeMember committeeMember = new CommitteeMember();
        BeanUtils.copyProperties(updateCommitteeMemberDto, committeeMember);
        return committeeMember;
    }
} 