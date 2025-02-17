package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.*;
import com.qcx.property.domain.vo.takeoutsRecord.TakeoutsVo;
import com.qcx.property.domain.vo.user.UserVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.enums.RoleType;
import com.qcx.property.enums.takeouts.CabinetStatusType;
import com.qcx.property.enums.takeouts.TakeoutsPlatformType;
import com.qcx.property.enums.takeouts.TakeoutsStatusType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.CabinetService;
import com.qcx.property.service.MessageService;
import com.qcx.property.service.RoleUserService;
import com.qcx.property.service.TakeoutPickupRecordService;
import com.qcx.property.mapper.TakeoutPickupRecordMapper;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.qcx.property.utils.Tools.generateOrderNumber;

/**
* @author 67121
* @description 针对表【takeout_pickup_record(外卖代收记录表)】的数据库操作Service实现
* @createDate 2025-02-14 17:10:17
*/
@Slf4j
@Service
public class TakeoutPickupRecordServiceImpl extends ServiceImpl<TakeoutPickupRecordMapper, TakeoutPickupRecord>
    implements TakeoutPickupRecordService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private MessageService messageService;

    @Resource
    private CabinetService cabinetService;

    @Resource
    private RoleUserService roleUserService;

    @Override
    public Page<TakeoutsVo> getAllTakeouts(QueryTakeoutsDto queryTakeoutsDto) {
        // 判空
        Optional.ofNullable(queryTakeoutsDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryTakeoutsDto.getId();
        String orderNumber = queryTakeoutsDto.getOrderNumber();
        Integer userId = queryTakeoutsDto.getUserId();
        String address = queryTakeoutsDto.getAddress();
        Integer courierId = queryTakeoutsDto.getCourierId();
        Integer platform = queryTakeoutsDto.getPlatform();
        Integer status = queryTakeoutsDto.getStatus();
        String remark = queryTakeoutsDto.getRemark();
        Integer cabinetNumber = queryTakeoutsDto.getCabinetNumber();
        Date notifyTime = queryTakeoutsDto.getNotifyTime();
        Date pickupTime = queryTakeoutsDto.getPickupTime();
        Date timeoutTime = queryTakeoutsDto.getTimeoutTime();
        Date createTime = queryTakeoutsDto.getCreateTime();
        Date updateTime = queryTakeoutsDto.getUpdateTime();

        QueryWrapper<TakeoutPickupRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(userId!= null, "userId", userId);
        queryWrapper.eq(courierId!= null, "courierId", courierId);
        queryWrapper.eq(platform!= null, "platform", platform);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(cabinetNumber!= null, "cabinetNumber", cabinetNumber);
        queryWrapper.like(orderNumber!= null, "orderNumber", orderNumber);
        queryWrapper.like(address!= null, "address", address);
        queryWrapper.like(remark!= null, "remark", remark);
        queryWrapper.eq(notifyTime!= null, "notifyTime", notifyTime);
        queryWrapper.eq(pickupTime!= null, "pickupTime", pickupTime);
        queryWrapper.eq(timeoutTime!= null, "timeoutTime", timeoutTime);
        queryWrapper.eq(createTime!= null, "createTime", createTime);
        queryWrapper.eq(updateTime!= null, "updateTime", updateTime);
        log.info("查询所有外卖订单");
        Page<TakeoutPickupRecord> page = this.page(new Page<>(queryTakeoutsDto.getCurrent(), queryTakeoutsDto.getPageSize()), queryWrapper);
        List<TakeoutsVo> takeoutsVoList = page.getRecords().stream().map(takeout -> {
            TakeoutsVo takeoutsVo = TakeoutsVo.takeoutsPickupRecordToVo(takeout);
            takeoutsVo.setCourier(UserVo.objToVo(userMapper.selectById(takeout.getCourierId())));
            takeoutsVo.setReceiveUser(UserVo.objToVo(userMapper.selectById(takeout.getUserId())));
            takeoutsVo.setPlatform(TakeoutsPlatformType.getRemarkById(takeout.getPlatform()));
            takeoutsVo.setStatus(TakeoutsStatusType.getRemarkById(takeout.getStatus()));
            takeoutsVo.setCabinet(cabinetService.getById(takeout.getCabinetNumber()));
            return takeoutsVo;
        }).toList();

        return new Page<TakeoutsVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(takeoutsVoList);
    }

    @Override
    public boolean updateTakeouts(UpdateTakeoutsDto updateTakeoutsDto) {
        // 判空
        Optional.ofNullable(updateTakeoutsDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateTakeoutsId = updateTakeoutsDto.getId();
        Optional.ofNullable(this.getById(updateTakeoutsId))
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        TakeoutPickupRecord updateTakeoutPickupRecord = UpdateTakeoutsDto.objToTakeouts(updateTakeoutsDto);

        boolean updateResult = this.updateById(updateTakeoutPickupRecord);
        log.info("更新外卖订单");

        return updateResult;
    }

    @Override
    public boolean updateTakeoutsByUser(UpdateTakeoutsByUserDto updateTakeoutsDto) {
        // 判空
        Optional.ofNullable(updateTakeoutsDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateTakeoutsId = updateTakeoutsDto.getId();
        Optional.ofNullable(this.getById(updateTakeoutsId))
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        TakeoutPickupRecord updateTakeoutPickupRecord = UpdateTakeoutsByUserDto.objToTakeouts(updateTakeoutsDto);

        boolean updateResult = this.updateById(updateTakeoutPickupRecord);
        log.info("（普通用户）更新外卖订单 id: {}", updateTakeoutPickupRecord.getId());

        return updateResult;
    }

    @Override
    public boolean addTakeouts(AddTakeoutsDto addTakeoutsDto) {
        // 判空
        if (addTakeoutsDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 参数判空
        Optional.ofNullable(addTakeoutsDto.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(addTakeoutsDto.getAddress())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(addTakeoutsDto.getPlatform())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 参数有效性校验
        // 收件人 id 有效性
        Optional.ofNullable(userMapper.selectById(addTakeoutsDto.getUserId()))
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_EXIST));

        // 添加外卖订单：外卖订单号生成
        String orderNumber = generateOrderNumber();
        TakeoutPickupRecord addTakeoutPickupRecord = AddTakeoutsDto.objToTakeouts(addTakeoutsDto);
        addTakeoutPickupRecord.setOrderNumber(orderNumber);
        boolean saveResult = this.save(addTakeoutPickupRecord);
        log.info("新增外卖订单");

        // 发送通知
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.TAKEOUT.getId());
        addMessageDto.setContent("您的外卖下单成功，等待骑手接单。");
        addMessageDto.setReceiveUser(addTakeoutsDto.getUserId());

        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (!sendMessageResult) {
            log.info("外卖订单号：{}，用户 id：{}，消息推送失败", orderNumber, addTakeoutsDto.getUserId());
        } else {
            log.info("外卖订单号：{}，用户 id：{}，消息推送成功", orderNumber, addTakeoutsDto.getUserId());
        }

        return saveResult;
    }

    @Override
    public boolean deleteTakeouts(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除外卖订单 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchTakeouts(Integer... takeoutIds) {
        // 判空
        if (takeoutIds == null || takeoutIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<TakeoutPickupRecord> takeoutPickupRecordList = this.listByIds(Arrays.asList(takeoutIds));
        if (takeoutPickupRecordList.size()!= takeoutIds.length) {
            throw new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(takeoutIds));
        log.info("批量删除外卖订单");

        return deleteResult > 0;
    }

    @Override
    public boolean acceptOrder(Integer userId, Integer takeoutsId) {
        // 判空
        if (userId == null || takeoutsId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        Optional.ofNullable(userMapper.selectById(userId))
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_EXIST));

        TakeoutPickupRecord takeoutsOrder = this.getById(takeoutsId);

        Optional.ofNullable(takeoutsOrder)
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        // 如果订单不是已下单的状态，则无法接单
        if (!takeoutsOrder.getStatus().equals(TakeoutsStatusType.ORDERED.getId())) {
            throw new BusinessException(ErrorType.TAKEOUTS_CANNOT_DELIVERY);
        }

        // 骑手接单
        takeoutsOrder.setCourierId(userId);
        takeoutsOrder.setTimeoutTime(Date.from(LocalDateTime.now().plusMinutes(40).atZone(ZoneId.systemDefault()).toInstant()));
        boolean updateResult = this.updateById(takeoutsOrder);
        log.info("骑手 id：{} 已接单", userId);

        // 消息通知
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.TAKEOUT.getId());
        addMessageDto.setContent("您的外卖已被骑手接单，请耐心等候");
        addMessageDto.setReceiveUser(takeoutsOrder.getUserId());
        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (sendMessageResult) {
            log.info("外卖 id：{} 已被骑手接单，骑手 id：{}，消息推送成功！", takeoutsId, userId);
        } else {
            log.info("外卖 id：{} 已被骑手接单，骑手 id：{}，消息推送失败！", takeoutsId, userId);
        }

        return updateResult;
    }

    @Override
    public boolean takeoutsArrive(Integer takeoutsId, Integer cabinetId, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        if (takeoutsId == null || cabinetId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        // 外卖柜
        Cabinet cabinet = cabinetService.getById(cabinetId);
        Optional.ofNullable(cabinet)
                .orElseThrow(() -> new BusinessException(ErrorType.CABINET_NOT_EXIST));
        if (!cabinet.getStatus().equals(CabinetStatusType.AVAILABLE.getId())) {
            throw new BusinessException(ErrorType.CABINET_NOT_AVAILABLE);
        }

        TakeoutPickupRecord takeoutsOrder = this.getById(takeoutsId);

        // 外卖订单状态
        if (!(takeoutsOrder.getStatus() == TakeoutsStatusType.DELIVERY.getId())) {
            throw new BusinessException(ErrorType.TAKEOUTS_NOT_DELIVERY);
        }

        // 修改外卖柜状态
        cabinetService.update(new UpdateWrapper<Cabinet>()
                .eq("id", cabinetId)
                .set("status", CabinetStatusType.OCCUPIED.getId())
        );

        // 送达
        boolean updateResult = this.update(new UpdateWrapper<TakeoutPickupRecord>()
                .eq("id", takeoutsId)
                .set("cabinetNumber", cabinetId)
                .set("notifyTime", new Date())
                .set("status", TakeoutsStatusType.DELIVERED.getId())
        );
        log.info("外卖 id：{} 已送达", takeoutsId);

        // 发送消息
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.TAKEOUT.getId());
        addMessageDto.setContent("您的外卖已送达，外卖柜编号：" + cabinet.getCode() + " 请及时取走!");
        addMessageDto.setReceiveUser(takeoutsOrder.getUserId());
        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (sendMessageResult) {
            log.info("外卖 id：{} 送达，用户 id：{}，消息推送成功！", takeoutsId, takeoutsOrder.getUserId());
        } else {
            log.info("外卖 id：{} 送达，用户 id：{}，消息推送失败！", takeoutsId, takeoutsOrder.getUserId());
        }

        return updateResult;
    }

    @Override
    public boolean deliverOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        if (takeoutsId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性
        TakeoutPickupRecord takeoutsOrder = this.getById(takeoutsId);
        Optional.ofNullable(takeoutsOrder)
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        // 如果不是 管理员/骑手，那么抛异常
        if (!(roleUserService.getOne(new QueryWrapper<RoleUser>()
                .eq("uid", loginUser.getUserId())).getRid().equals(RoleType.ADMIN.getRoleId()) && !Objects.equals(loginUser.getUserId(), takeoutsOrder.getCourierId()))) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }

        // 开始派送
        takeoutsOrder.setStatus(TakeoutsStatusType.DELIVERY.getId());
        boolean updateResult = this.updateById(takeoutsOrder);
        log.info("订单开始派送，userId: {}", loginUser.getUserId());

        // 发送消息
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.TAKEOUT.getId());
        addMessageDto.setContent("您的外卖开始派送，请耐心等候");
        addMessageDto.setReceiveUser(takeoutsOrder.getUserId());
        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (sendMessageResult) {
            log.info("外卖 id：{} 开始派送，消息推送成功！", takeoutsId);
        } else {
            log.info("外卖 id：{} 开始派送，消息推送失败！", takeoutsId);
        }

        return updateResult;
    }

    @Override
    public boolean receiveOrder(Integer takeoutsId, HttpServletRequest request) throws JsonProcessingException {
        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));
        if (loginUser == null) {
            throw new BusinessException(ErrorType.TOKEN_INVALID);
        }

        if (takeoutsId == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性
        TakeoutPickupRecord takeoutsOrder = this.getById(takeoutsId);
        Optional.ofNullable(takeoutsOrder)
                .orElseThrow(() -> new BusinessException(ErrorType.TAKEOUTS_NOT_EXIST));

        // 查看用户权限：如果不是 admin 或 订单用户，则无法取件
        if (!(roleUserService.getOne(new QueryWrapper<RoleUser>()
                .eq("uid", loginUser.getUserId())).getRid().equals(RoleType.ADMIN.getRoleId()) && !Objects.equals(loginUser.getUserId(), takeoutsOrder.getUserId()))) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }

        // 修改外卖柜状态
        cabinetService.update(new UpdateWrapper<Cabinet>()
                .eq("id", takeoutsOrder.getCabinetNumber())
                .set("status", CabinetStatusType.AVAILABLE.getId())
        );

        // 领取外卖
        takeoutsOrder.setStatus(TakeoutsStatusType.FINISHED.getId());
        takeoutsOrder.setPickupTime(new Date());
        boolean updateResult = this.updateById(takeoutsOrder);
        log.info("外卖订单 id：{} 被领取，用户 id：{}", takeoutsId, loginUser.getUserId());

        // 发送消息
        AddMessageDto addMessageDto = new AddMessageDto();
        addMessageDto.setType(MessageType.TAKEOUT.getId());
        addMessageDto.setContent("您的外卖已取走，请确认是本人操作，如有异常请联系管理员");
        addMessageDto.setReceiveUser(takeoutsOrder.getUserId());
        boolean sendMessageResult = messageService.addMessage(addMessageDto);
        if (sendMessageResult) {
            log.info("外卖 id：{} 已被领取，用户 id：{}，消息推送成功！", takeoutsId, loginUser.getUserId());
        } else {
            log.info("外卖 id：{} 已被领取，用户 id：{}，消息推送失败！", takeoutsId, loginUser.getUserId());
        }
        return updateResult;
    }
}




