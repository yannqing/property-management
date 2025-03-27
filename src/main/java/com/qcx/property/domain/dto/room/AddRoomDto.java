package com.qcx.property.domain.dto.room;

import com.qcx.property.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 新增房间 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddRoomDto", description = "新增房间请求参数")
public class AddRoomDto implements Serializable {

    /**
     * 房间名
     */
    @Schema(description = "房间名", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    /**
     * 房间号
     */
    @Schema(description = "房间号", requiredMode = Schema.RequiredMode.REQUIRED)
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

    public static Room objToRoom(AddRoomDto addRoomDto) {
        if (addRoomDto == null) {
            return null;
        }

        Room room = new Room();
        BeanUtils.copyProperties(addRoomDto, room);
        return room;
    }
}
