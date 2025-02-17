package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 67121
* @description 针对表【takeout_pickup_record(外卖代收记录表)】的数据库操作Service
* @createDate 2025-02-14 17:10:17
*/
public interface TakeoutPickupRecordService extends IService<TakeoutPickupRecord> {

    /**
     * 查询所有外卖订单（管理员）
     * @param queryTakeoutsDto 查询 dto
     * @return 返回查询结果
     */
    Page<TakeoutPickupRecord> getAllTakeouts(QueryTakeoutsDto queryTakeoutsDto);

    /**
     * 更新外卖订单（管理员）
     * @param updateTakeoutsDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateTakeouts(UpdateTakeoutsDto updateTakeoutsDto);

    /**
     * 更新外卖订单（普通用户）
     * @param updateTakeoutsDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateTakeoutsByUser(UpdateTakeoutsByUserDto updateTakeoutsDto);


    /**
     * 新增外卖订单
     * @param addTakeoutsDto 新增 dto
     * @return 返回新增结果
     */
    boolean addTakeouts(AddTakeoutsDto addTakeoutsDto);

    /**
     * 删除单个外卖订单
     * @param id 删除 id
     * @return 返回删除结果
     */
    boolean deleteTakeouts(Integer id);

    /**
     * 批量删除外卖订单
     * @param takeoutIds id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchTakeouts(Integer... takeoutIds);

    /**
     * 随机生成外卖订单号
     * @return 返回结果
     */
    String generateOrderNumber();
}
