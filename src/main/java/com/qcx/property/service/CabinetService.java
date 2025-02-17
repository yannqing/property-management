package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.takeouts.AddCabinetDto;
import com.qcx.property.domain.dto.takeouts.QueryCabinetDto;
import com.qcx.property.domain.dto.takeouts.UpdateCabinetDto;
import com.qcx.property.domain.entity.Cabinet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 67121
* @description 针对表【cabinet(外卖柜表)】的数据库操作Service
* @createDate 2025-02-17 12:17:14
*/
public interface CabinetService extends IService<Cabinet> {

    /**
     * 查询全部外卖柜
     * @param queryCabinetDto 查询 dto
     * @return 返回查询结果
     */
    Page<Cabinet> getAllCabinets(QueryCabinetDto queryCabinetDto);

    /**
     * 更新外卖柜
     * @param updateCabinetDto 更新 dto
     * @return 更新结果
     */
    boolean updateCabinet(UpdateCabinetDto updateCabinetDto);

    /**
     * 新增外卖柜
     * @param addCabinetDto 新增 dto
     * @return 新增外卖柜结果
     */
    boolean addCabinet(AddCabinetDto addCabinetDto);

    /**
     * 删除单个 外卖柜
     * @param id 外卖柜 id
     * @return 删除结果
     */
    boolean deleteCabinet(Integer id);

    /**
     * 批量删除外卖柜
     * @param cabinetIds 外卖柜 id 数组
     * @return 删除结果
     */
    boolean deleteBatchCabinet(Integer... cabinetIds);
}

