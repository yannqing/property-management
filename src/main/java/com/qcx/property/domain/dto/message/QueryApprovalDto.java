package com.qcx.property.domain.dto.message;

import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 管理员查询所有审批通知 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryApprovalDto", description = "管理员查询所有审批通知")
public class QueryApprovalDto extends PageRequest {
    /**
     * 消息状态
     */
    @Schema(description = "消息状态（2待审批，3已审批）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String username;

    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickname;


}
