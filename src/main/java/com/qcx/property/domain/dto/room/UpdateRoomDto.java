package com.qcx.property.domain.dto.room;

import com.qcx.property.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @description: 更新房间 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateRoomDto", description = "修改房间对象")
public class UpdateRoomDto {

    /**
     * 主键id
     */
    @Schema(description = "主键id", requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static Room objToRoom(UpdateRoomDto updateRoomDto) {
        if (updateRoomDto == null) {
            return null;
        }

        Room room = new Room();
        BeanUtils.copyProperties(updateRoomDto, room);
        return room;
    }
}
