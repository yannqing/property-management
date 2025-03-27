package com.qcx.property.domain.dto.room;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qcx.property.domain.model.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 查询所有房间 dto
 * @author: yannqing
 * @create: 2025-02-08 17:18
 * @from: <更多资料：yannqing.com>
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(name = "QueryRoomDto", description = "查询所有房间请求参数")
public class QueryRoomDto extends PageRequest implements Serializable {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer id;

    /**
     * 房间名
     */
    @Schema(description = "房间名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 房间号
     */
    @Schema(description = "房间号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer num;

    /**
     * 租客
     */
    @Schema(description = "租客", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer tenant;

    /**
     * 业主
     */
    @Schema(description = "业主", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer owner;

    /**
     * 租金
     */
    @Schema(description = "租金", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal price;

    /**
     * 状态（0未出售，1未出租，1已出租）
     */
    @Schema(description = "状态（0未出售，1未出租，1已出租）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Serial
    private static final long serialVersionUID = 1L;
}
