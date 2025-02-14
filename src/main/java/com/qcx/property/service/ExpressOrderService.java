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

    Page<ExpressOrder> getAllExpressOrders(QueryExpressOrderDto queryExpressOrderDto);

    Page<ExpressOrderVo> getMyselfExpressOrders(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException;

    boolean updateExpressOrder(UpdateExpressOrderDto updateExpressOrderDto);

    boolean addExpressOrder(AddExpressOrderDto addExpressOrderDto);

    boolean deleteExpressOrder(Integer id);

    boolean deleteBatchExpressOrder(Integer... expressOrderIds);
}
