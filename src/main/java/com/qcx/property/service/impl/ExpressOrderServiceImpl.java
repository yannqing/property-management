package com.qcx.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.ExpressOrder.AddExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.QueryExpressOrderDto;
import com.qcx.property.domain.dto.ExpressOrder.UpdateExpressOrderDto;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.message.UpdateMessageDto;
import com.qcx.property.domain.entity.CommunityActivity;
import com.qcx.property.domain.entity.ExpressOrder;
import com.qcx.property.domain.entity.Message;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.MessageContent;
import com.qcx.property.domain.model.PageRequest;
import com.qcx.property.domain.vo.ExpressOrder.ExpressOrderAdminVo;
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
import com.qcx.property.service.MessageService;
import com.qcx.property.utils.JwtUtils;
import com.qcx.property.utils.Tools;
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

    @Resource
    private MessageService messageService;

    @Override
    public Page<ExpressOrderAdminVo> getAllExpressOrders(QueryExpressOrderDto queryExpressOrderDto) {
        // 判空
        Optional.ofNullable(queryExpressOrderDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryExpressOrderDto.getId();
        Integer pickupUser = queryExpressOrderDto.getPickupUser();
        Integer userId = queryExpressOrderDto.getUserId();
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
        Page<ExpressOrder> page = this.page(new Page<>(queryExpressOrderDto.getCurrent(), queryExpressOrderDto.getPageSize()), queryWrapper);
        List<ExpressOrderAdminVo> expressOrderAdminVoList = page.getRecords().stream().map(expressOrder -> {
            ExpressOrderAdminVo expressOrderAdminVo = ExpressOrderAdminVo.expressOrderToVo(expressOrder);
            expressOrderAdminVo.setPickupUser(UserVo.objToVo(userMapper.selectById(expressOrder.getPickupUser())));
            expressOrderAdminVo.setUserId(UserVo.objToVo(userMapper.selectById(expressOrder.getUserId())));
            expressOrderAdminVo.setExpressCompany(ExpressCompanyType.getRemarkById(expressOrder.getExpressCompany()));
            expressOrderAdminVo.setStatus(ExpressStatusType.getRemarkById(expressOrder.getStatus()));

            return expressOrderAdminVo;
        }).toList();

        return new Page<ExpressOrderAdminVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(expressOrderAdminVoList);
    }

    @Override
    public Page<ExpressOrderVo> getMyselfExpressOrders(PageRequest pageRequest, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 查询所有信息
        List<ExpressOrder> allExpressOrderInfo = this.baseMapper.selectList(new QueryWrapper<ExpressOrder>().eq("userId", loginUser.getUserId()));

        // 二次封装
        List<ExpressOrderVo> expressOrderVoList = allExpressOrderInfo.stream().map(expressOrder -> {
            ExpressOrderVo expressOrderVo = ExpressOrderVo.objToVo(expressOrder);
            expressOrderVo.setPickupUser(UserVo.objToVo(userMapper.selectById(expressOrder.getPickupUser())));
            expressOrderVo.setUserId(UserVo.objToVo(userMapper.selectById(expressOrder.getUserId())));
            expressOrderVo.setExpressCompany(ExpressCompanyType.getRemarkById(expressOrder.getExpressCompany()));
            expressOrderVo.setStatus(ExpressStatusType.getRemarkById(expressOrder.getStatus()));

            return expressOrderVo;
        }).toList();
        log.info("查询所有快递订单(个人)");

        return new Page<ExpressOrderVo>(pageRequest.getCurrent(), pageRequest.getPageSize()).setRecords(expressOrderVoList);
    }

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

    @Override
    public boolean addExpressOrder(AddExpressOrderDto addExpressOrderDto) {
        // 判空
        // 用户id不能为空
        Optional.ofNullable(addExpressOrderDto.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        //快递公司id不能为空
        Optional.ofNullable(addExpressOrderDto.getExpressCompany())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        // 添加消息通知
        ExpressOrder expressOrder = AddExpressOrderDto.objToExpressOrder(addExpressOrderDto);
        expressOrder.setTrackingNumber(Tools.generateOrderNumber());
        boolean saveResult = this.save(expressOrder);
        log.info("新增快递订单");

        // TODO 发送消息通知
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.EXPRESS.getId());
        MessageContent<?> addMessageContent = new MessageContent<>();
        addMessageContent.setNotify("您有新的快递订单，请核实");
        addMessageDto.setContent(JSON.toJSONString(addMessageContent));
        addMessageDto.setReceiveUser(addExpressOrderDto.getUserId());


        return saveResult;
    }

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

    @Override
    public boolean pickUpExpress(Integer expressOderId, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        ExpressOrder pickupedExpressOrder = this.getById(expressOderId);
        // id 校验
        Optional.ofNullable(pickupedExpressOrder)
                .orElseThrow(() -> new BusinessException(ErrorType.EXPRESS_ORDER_NOT_EXIST));

        // 检查快递的状态是否可取
        if (!pickupedExpressOrder.getStatus().equals(ExpressStatusType.STORED.getId())) {
            throw new BusinessException(ErrorType.EXPRESS_CANNOT_PICK_UP);
        }

        // 取件: 修改取件用户为登录用户；修改快递状态为已取件
        boolean updateResult = this.update(new UpdateWrapper<ExpressOrder>()
                .eq("id", expressOderId)
                .set("status", ExpressStatusType.PICKED_UP.getId())
                .set("pickupUser", loginUser.getUserId())
        );

        // 发送消息通知订单用户，快递已经被取件
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.EXPRESS.getId());
        MessageContent<?> addMessageContent = new MessageContent<>();
        addMessageContent.setNotify("您的快递被取走，请确认");
        addMessageDto.setContent(JSON.toJSONString(addMessageContent));
        addMessageDto.setReceiveUser(pickupedExpressOrder.getUserId());
        addMessageDto.setStatus(0);

        boolean addMessageResult = messageService.addMessage(addMessageDto);
        log.info("取件：id：{}, 取件人：{}", expressOderId, loginUser.getUserId());
        if (!addMessageResult) {
            log.error("取件：id：{}, 取件人：{}, 消息推送失败", expressOderId, loginUser.getUserId());
        }

        //返回结果
        return updateResult;
    }

    @Override
    public boolean confirmExpress(Integer expressOderId, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        ExpressOrder confirmedExpressOrder = this.getById(expressOderId);
        // id 校验
        Optional.ofNullable(confirmedExpressOrder)
                .orElseThrow(() -> new BusinessException(ErrorType.EXPRESS_ORDER_NOT_EXIST));

        // 校验此快递的订单用户是否是自己
        if (!confirmedExpressOrder.getUserId().equals(loginUser.getUserId())) {
            throw new BusinessException(ErrorType.EXPRESS_IS_NOT_BELONG_YOU);
        }

        // 检查此快递的状态是否是已取件
        if (!confirmedExpressOrder.getStatus().equals(ExpressStatusType.PICKED_UP.getId())) {
            throw new BusinessException(ErrorType.EXPRESS_CANNOT_CONFIRM);
        }

        // 确认
        boolean updateResult = this.update(new UpdateWrapper<ExpressOrder>()
                .eq("id", expressOderId)
                .set("status", ExpressStatusType.FINISHED)
        );

        // 发送通知
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.EXPRESS.getId());
        MessageContent<?> addMessageContent = new MessageContent<>();
        addMessageContent.setNotify("您的快递已确认领取，请注意！");
        addMessageDto.setContent(JSON.toJSONString(addMessageContent));
        addMessageDto.setReceiveUser(loginUser.getUserId());

        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        log.info("确认快递：id：{}, 取件人：{}", expressOderId, loginUser.getUserId());
        if (!sendMessageResult) {
            log.info("确认快递：id：{}, 取件人：{}, 消息推送失败", expressOderId, loginUser.getUserId());
        }

        return updateResult;
    }
}




