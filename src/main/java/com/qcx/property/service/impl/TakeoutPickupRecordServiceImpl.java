package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.message.AddMessageDto;
import com.qcx.property.domain.dto.takeouts.AddTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.QueryTakeoutsDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsByUserDto;
import com.qcx.property.domain.dto.takeouts.UpdateTakeoutsDto;
import com.qcx.property.domain.entity.CostType;
import com.qcx.property.domain.entity.TakeoutPickupRecord;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.enums.MessageType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.UserMapper;
import com.qcx.property.service.MessageService;
import com.qcx.property.service.TakeoutPickupRecordService;
import com.qcx.property.mapper.TakeoutPickupRecordMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public Page<TakeoutPickupRecord> getAllTakeouts(QueryTakeoutsDto queryTakeoutsDto) {
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

        return this.page(new Page<>(queryTakeoutsDto.getCurrent(), queryTakeoutsDto.getPageSize()), queryWrapper);
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

    public String generateOrderNumber() {
        // 获取当前时间戳
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());

        // 生成一个随机数
        Random random = new Random();
        int randomNumber = random.nextInt(999999);

        // 生成一个UUID的一部分
        String uuidPart = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        // 组合生成订单号
        return timestamp + String.format("%06d", randomNumber) + uuidPart;
    }
}




