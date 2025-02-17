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

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 新增外卖柜 dto
 * @author: yannqing
 * @create: 2025-02-08 18:10
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "AddCabinetDto", description = "新增外卖柜请求参数")
public class AddCabinetDto implements Serializable {

    /**
     * 外卖柜编号
     */
    @Schema(description = "外卖柜编号", requiredMode = Schema.RequiredMode.REQUIRED)
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

    @Serial
    private static final long serialVersionUID = 1L;

    public static Cabinet objToCabinet(AddCabinetDto addCabinetDto) {
        if (addCabinetDto == null) {
            return null;
        }

        Cabinet cabinet = new Cabinet();
        BeanUtils.copyProperties(addCabinetDto, cabinet);
        return cabinet;
    }
}
