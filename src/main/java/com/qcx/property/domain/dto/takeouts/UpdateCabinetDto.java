package com.qcx.property.domain.dto.takeouts;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.qcx.property.domain.entity.Cabinet;
import com.qcx.property.domain.entity.CostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @description: 更新外卖柜 dto
 * @author: yannqing
 * @create: 2025-02-08 17:44
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "UpdateCabinetDto", description = "修改外卖柜对象")
public class UpdateCabinetDto {
    /**
     * 主键 id
     */
    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer id;

    /**
     * 外卖柜编号
     */
    @Schema(description = "外卖柜编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String code;

    /**
     * 状态（可用/占用/异常）
     */
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer status;

    /**
     * 类型
     */
    @Schema(description = "类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Integer type;

    /**
     * 备注
     */
    @Schema(description = "备注", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    public static Cabinet objToCabinet(UpdateCabinetDto updateCabinetDto) {
        if (updateCabinetDto == null) {
            return null;
        }

        Cabinet cabinet = new Cabinet();
        BeanUtils.copyProperties(updateCabinetDto, cabinet);
        return cabinet;
    }
}
