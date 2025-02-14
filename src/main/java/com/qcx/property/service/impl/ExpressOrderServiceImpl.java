package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.ExpressOrder.AddExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.QueryExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.UpdateExpressOrderDto;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.message.UpdateMessageDto;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.entity.Message;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.ExpressOrder.ExpressOrderVo;
import com.qcx.property.domain.vo.message.MessageVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.enums.expressOrder.ExpressCompanyType;
import com.qcx.property.enums.expressOrder.ExpressStatusType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.ExpressOrderService;
import com.qcx.property.mapper.ExpressOrderMapper;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【express_order(快递订单表)】的数据库操作Service实现
* @createDate 2025-02-14 17:10:04
*/
@Slf4j
@Service
public class ExpressOrderServiceImpl extends ServiceImpl<ExpressOrderMapper, ExpressOrder>
    implements ExpressOrderService{

    @Resource
    private UserMapper userMapper;


    /**
     * 查询所有快递订单（仅限管理员）
     * @param queryExpressOrderDto 查询dto
     * @return 分页结果
     */
    @Override
    public Page<ExpressOrder> getAllExpressOrders(QueryExpressOrderDto queryExpressOrderDto) {
        // 判空
        Optional.ofNullable(queryExpressOrderDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryExpressOrderDto.getId();
        Integer pickupUser = queryExpressOrderDto.getPickupUser();
        String userId = queryExpressOrderDto.getUserId();
        String pickupCode = queryExpressOrderDto.getPickupCode();
        String trackingNumber = queryExpressOrderDto.getTrackingNumber();
        Integer expressCompany = queryExpressOrderDto.getExpressCompany();
        Integer status = queryExpressOrderDto.getStatus();
        Date createTime = queryExpressOrderDto.getCreateTime();
        Date updateTime = queryExpressOrderDto.getUpdateTime();

        QueryWrapper<ExpressOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(pickupUser!= null, "pickupUser", pickupUser);
        queryWrapper.like(pickupCode!= null, "content", pickupCode);
        queryWrapper.like(trackingNumber!= null, "trackingNumber", trackingNumber);
        queryWrapper.eq(createTime!= null, "createTime", createTime);
        queryWrapper.eq(updateTime!= null, "updateTime", updateTime);
        queryWrapper.eq(expressCompany!= null, "expressCompany", expressCompany);
        queryWrapper.eq(userId!= null, "userId", userId);
        queryWrapper.eq(status!= null, "description", status);
        log.info("查询所有快递订单");

        return this.page(new Page<>(queryExpressOrderDto.getCurrent(), queryExpressOrderDto.getPageSize()), queryWrapper);
    }

    /**
     * 查询所有快递订单（个人）
     * @param pageRequest 分页请求
     * @param request 会话
     * @return 快递订单封装
     * @throws JsonProcessingException 异常
     */
    @Override
    public Page<ExpressOrderVo> getMyselfExpressOrders(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 查询所有信息
        List<ExpressOrder> allExpressOrderInfo = this.baseMapper.selectList(new QueryWrapper<ExpressOrder>().eq("receiveUser", loginUser.getUserId()));

        // 二次封装
        List<ExpressOrderVo> expressOrderVoList = allExpressOrderInfo.stream().map(expressOrder -> {
            ExpressOrderVo expressOrderVo = ExpressOrderVo.objToVo(expressOrder);
            expressOrderVo.setPickupUser(UserVo.objToVo(userMapper.selectById(expressOrder.getPickupUser())));
            expressOrderVo.setUserId(UserVo.objToVo(userMapper.selectById(expressOrder.getUserId())));
            expressOrderVo.setExpressCompany(ExpressCompanyType.getExpressCompanyById(expressOrder.getExpressCompany()));
            expressOrderVo.setStatus(ExpressStatusType.getExpressStatusById(expressOrder.getStatus()));

            return expressOrderVo;
        }).toList();
        log.info("查询所有快递订单(个人)");

        return new Page<ExpressOrderVo>(pageRequest.getCurrent(), pageRequest.getPageSize()).setRecords(expressOrderVoList);
    }

    /**
     * 更新快递订单
     * @param updateExpressOrderDto 更新dto
     * @return 更新结果
     */
    @Override
    public boolean updateExpressOrder(UpdateExpressOrderDto updateExpressOrderDto) {
        // 判空
        Optional.ofNullable(updateExpressOrderDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateMessageId = updateExpressOrderDto.getId();
        Optional.ofNullable(this.getById(updateMessageId))
                .orElseThrow(() -> new BusinessException(ErrorType.COST_NOT_EXIST));

        ExpressOrder updateExpressOrder = UpdateExpressOrderDto.objToExpressOrder(updateExpressOrderDto);

        boolean updateResult = this.updateById(updateExpressOrder);
        log.info("更新快递订单");

        return updateResult;
    }

    /**
     * 新增快递订单
     * @param addExpressOrderDto 新增 dto
     * @return 新增结果
     */
    @Override
    public boolean addExpressOrder(AddExpressOrderDto addExpressOrderDto) {
        // 判空
        // 用户id不能为空
        Optional.ofNullable(addExpressOrderDto.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //快递单号不能为空
        Optional.ofNullable(addExpressOrderDto.getTrackingNumber())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //快递公司id不能为空
        Optional.ofNullable(addExpressOrderDto.getExpressCompany())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        // 添加消息通知
        ExpressOrder expressOrder = AddExpressOrderDto.objToExpressOrder(addExpressOrderDto);
        boolean saveResult = this.save(expressOrder);
        log.info("新增快递订单");

        return saveResult;
    }

    /**
     * 删除单个快递订单（仅限管理员）
     * @param id 要删除的快递订单id
     * @return 返回删除结果
     */
    @Override
    public boolean deleteExpressOrder(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.MESSAGE_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除快递订单（id：{}）", id);

        return deleteResult;
    }

    /**
     * 批量删除消快递订单（仅限管理员）
     * @param expressOrderIds 要删除的快递订单 id 数组
     * @return 返回删除结果
     */
    @Override
    public boolean deleteBatchExpressOrder(Integer... expressOrderIds) {
        // 判空
        if (expressOrderIds == null || expressOrderIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<ExpressOrder> expressOrderList = this.listByIds(Arrays.asList(expressOrderIds));
        if (expressOrderList.size()!= expressOrderIds.length) {
            throw new BusinessException(ErrorType.EXPRESS_ORDER_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(expressOrderIds));
        log.info("批量删除快递订单");

        return deleteResult > 0;
    }
}




