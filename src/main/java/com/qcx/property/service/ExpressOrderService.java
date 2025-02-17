package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.ExpressOrder.AddExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.QueryExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.UpdateExpressOrderDto;
import com.qcx.property.domain.entity.ExpressOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.ExpressOrder.ExpressOrderVo;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 67121
* @description 针对表【express_order(快递订单表)】的数据库操作Service
* @createDate 2025-02-14 17:10:04
*/
public interface ExpressOrderService extends IService<ExpressOrder> {

    /**
     * 查询所有快递订单（仅限管理员）
     * @param queryExpressOrderDto 查询dto
     * @return 分页结果
     */
    Page<ExpressOrder> getAllExpressOrders(QueryExpressOrderDto queryExpressOrderDto);

    /**
     * 查询所有快递订单（个人）
     * @param pageRequest 分页请求
     * @param request 会话
     * @return 快递订单封装
     * @throws JsonProcessingException 异常
     */
    Page<ExpressOrderVo> getMyselfExpressOrders(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 更新快递订单
     * @param updateExpressOrderDto 更新dto
     * @return 更新结果
     */
    boolean updateExpressOrder(UpdateExpressOrderDto updateExpressOrderDto);

    /**
     * 新增快递订单
     * @param addExpressOrderDto 新增 dto
     * @return 新增结果
     */
    boolean addExpressOrder(AddExpressOrderDto addExpressOrderDto);

    /**
     * 删除单个快递订单（仅限管理员）
     * @param id 要删除的快递订单id
     * @return 返回删除结果
     */
    boolean deleteExpressOrder(Integer id);

    /**
     * 批量删除消快递订单（仅限管理员）
     * @param expressOrderIds 要删除的快递订单 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchExpressOrder(Integer... expressOrderIds);

    /**
     * 取件
     * @param expressOderId 快递订单 id
     * @param request 会话 request
     * @return 返回取件结果
     * @throws JsonProcessingException token 解析异常
     */
    boolean pickUpExpress(Integer expressOderId, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 确认快递
     * @param expressOderId 快递订单 id
     * @param request 会话 request
     * @return 确认结果
     * @throws JsonProcessingException token 解析异常
     */
    boolean confirmExpress(Integer expressOderId, HttpServletRequest request) throws JsonProcessingException;
}
