package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.takeoutsRecord.TakeoutsVo;
import jakarta.servlet.http.HttpServletRequest;

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
    Page<TakeoutsVo> getAllTakeouts(QueryTakeoutsDto queryTakeoutsDto);

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
     * 骑手接单
     * @param userId 骑手 id
     * @param takeoutsId 外卖订单 id
     * @return 接单结果
     */
    boolean acceptOrder(Integer userId, Integer takeoutsId);

    /**
     * 订单送达
     * @param takeoutsId 外卖订单 id
     * @param cabinetId 外卖柜 id
     * @param request 会话 request
     * @return 送达结果
     * @throws JsonProcessingException 异常
     */
    boolean takeoutsArrive(Integer takeoutsId, Integer cabinetId, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 开始派送订单
     * @param takeoutsId 派送的外卖 id
     * @param request 会话 request
     * @return 派送结果
     * @throws JsonProcessingException 异常
     */
    boolean deliverOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 领取外卖订单
     * @param takeoutsId 外卖订单 id
     * @param request 会话 request
     * @return 返回结果
     * @throws JsonProcessingException 异常
     */
    boolean receiveOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException;
}
