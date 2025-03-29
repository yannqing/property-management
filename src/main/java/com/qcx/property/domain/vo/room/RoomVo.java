package com.qcx.property.domain.vo.room;

import com.qcx.property.domain.entity.Room;
import com.qcx.property.domain.model.RoomModel;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomVo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 房间名
     */
    private String name;

    /**
     * 房间号
     */
    private Integer num;

    /**
     * 租客
     */
    private Integer tenant;

    /**
     * 业主
     */
    private Integer owner;

    /**
     * 租金
     */
    private BigDecimal price;

    /**
     * 状态（0未出售，1未出租，1已出租）
     */
    private Integer status;

    /**
     * 其他
     */
    private RoomModel roomModel;

    public static RoomVo convert(Room room) {
        if (room == null) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        RoomVo roomVo = new RoomVo();
        BeanUtils.copyProperties(room, roomVo);
        return roomVo;
    }


}
