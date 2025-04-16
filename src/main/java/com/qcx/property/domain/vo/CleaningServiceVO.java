package com.qcx.property.domain.vo;

import com.qcx.property.domain.entity.CleaningService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 保洁服务视图对象
 * @author: yannqing
 * @create: 2025-02-09 10:50
 * @from: <更多资料：yannqing.com>
 **/
@Data
@Schema(name = "CleaningServiceVO", description = "保洁服务视图对象")
public class CleaningServiceVO implements Serializable {
    /**
     * 保洁服务id
     */
    @Schema(description = "保洁服务id")
    private Integer id;

    /**
     * 保洁服务名称
     */
    @Schema(description = "保洁服务名称")
    private String name;

    /**
     * 保洁类型id
     */
    @Schema(description = "保洁类型id")
    private Integer type;

    /**
     * 保洁类型名称
     */
    @Schema(description = "保洁类型名称")
    private String typeName;

    /**
     * 服务描述
     */
    @Schema(description = "服务描述")
    private String description;

    /**
     * 服务价格
     */
    @Schema(description = "服务价格")
    private BigDecimal price;

    /**
     * 服务时长(分钟)
     */
    @Schema(description = "服务时长(分钟)")
    private Integer duration;

    /**
     * 状态(0禁用,1启用)
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    public static CleaningServiceVO entityToVO(CleaningService cleaningService) {
        if (cleaningService == null) {
            return null;
        }
        CleaningServiceVO vo = new CleaningServiceVO();
        BeanUtils.copyProperties(cleaningService, vo);
        return vo;
    }
} 