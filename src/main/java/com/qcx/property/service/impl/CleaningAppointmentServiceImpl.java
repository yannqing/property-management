package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.cleaning.AddCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningAppointmentDto;
import com.qcx.property.domain.entity.CleaningAppointment;
import com.qcx.property.domain.entity.CleaningService;
import com.qcx.property.domain.vo.CleaningAppointmentVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.CleaningAppointmentMapper;
import com.qcx.property.mapper.CleaningServiceMapper;
import com.qcx.property.service.CleaningAppointmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author yannqing
* @description 针对表【cleaning_appointment】的数据库操作Service实现
* @createDate 2025-02-09 11:20:02
*/
@Slf4j
@Service
public class CleaningAppointmentServiceImpl extends ServiceImpl<CleaningAppointmentMapper, CleaningAppointment>
    implements CleaningAppointmentService{

    @Resource
    private CleaningServiceMapper cleaningServiceMapper;

    @Override
    public Page<CleaningAppointmentVO> getCleaningAppointments(QueryCleaningAppointmentDto queryCleaningAppointmentDto) {
        // 判空
        Optional.ofNullable(queryCleaningAppointmentDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCleaningAppointmentDto.getId();
        Integer serviceId = queryCleaningAppointmentDto.getServiceId();
        Integer userId = queryCleaningAppointmentDto.getUserId();
        Date startTime = queryCleaningAppointmentDto.getStartTime();
        Date endTime = queryCleaningAppointmentDto.getEndTime();
        Integer status = queryCleaningAppointmentDto.getStatus();
        Integer paymentStatus = queryCleaningAppointmentDto.getPaymentStatus();

        QueryWrapper<CleaningAppointment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(serviceId != null, "serviceId", serviceId);
        queryWrapper.eq(userId != null, "userId", userId);
        queryWrapper.eq(status != null, "status", status);
        queryWrapper.eq(paymentStatus != null, "paymentStatus", paymentStatus);
        
        if (startTime != null) {
            queryWrapper.ge("appointmentTime", startTime);
        }
        
        if (endTime != null) {
            queryWrapper.le("appointmentTime", endTime);
        }
        
        log.info("查询保洁预约");

        // 分页查询
        Page<CleaningAppointment> page = this.page(
                new Page<>(queryCleaningAppointmentDto.getCurrent(), queryCleaningAppointmentDto.getPageSize()),
                queryWrapper
        );

        // 转换为VO
        List<CleaningAppointmentVO> voList = convertToVOList(page.getRecords());

        // 构造返回结果
        Page<CleaningAppointmentVO> resultPage = new Page<>();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setSize(page.getSize());
        resultPage.setTotal(page.getTotal());
        resultPage.setRecords(voList);

        return resultPage;
    }

    @Override
    public CleaningAppointmentVO getCleaningAppointmentById(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询
        CleaningAppointment cleaningAppointment = this.getById(id);

        // 判断是否存在
        if (cleaningAppointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 转换为VO并返回
        return enrichCleaningAppointmentVO(CleaningAppointmentVO.entityToVO(cleaningAppointment));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addCleaningAppointment(AddCleaningAppointmentDto addCleaningAppointmentDto) {
        // 判空
        Optional.ofNullable(addCleaningAppointmentDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 检查服务是否存在且可用
        Integer serviceId = addCleaningAppointmentDto.getServiceId();
        CleaningService cleaningService = cleaningServiceMapper.selectById(serviceId);
        if (cleaningService == null) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
        }
        
        if (cleaningService.getStatus() == 0) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_DISABLED);
        }

        // 检查预约时间是否有效（不能是过去的时间）
        Date appointmentTime = addCleaningAppointmentDto.getAppointmentTime();
        if (appointmentTime == null || appointmentTime.before(new Date())) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_TIME_INVALID);
        }

        // 转换为实体
        CleaningAppointment cleaningAppointment = AddCleaningAppointmentDto.objToCleaningAppointment(addCleaningAppointmentDto);

        // 保存
        log.info("新增保洁预约");
        return this.save(cleaningAppointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCleaningAppointment(UpdateCleaningAppointmentDto updateCleaningAppointmentDto) {
        // 判空
        Optional.ofNullable(updateCleaningAppointmentDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(updateCleaningAppointmentDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 检查是否存在
        Integer appointmentId = updateCleaningAppointmentDto.getId();
        CleaningAppointment existAppointment = this.getById(appointmentId);
        if (existAppointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查服务是否存在且可用
        Integer serviceId = updateCleaningAppointmentDto.getServiceId();
        if (serviceId != null) {
            CleaningService cleaningService = cleaningServiceMapper.selectById(serviceId);
            if (cleaningService == null) {
                throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
            }
            
            if (cleaningService.getStatus() == 0) {
                throw new BusinessException(ErrorType.CLEANING_SERVICE_DISABLED);
            }
        }

        // 检查预约时间是否有效（不能是过去的时间）
        Date appointmentTime = updateCleaningAppointmentDto.getAppointmentTime();
        if (appointmentTime != null && appointmentTime.before(new Date())) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_TIME_INVALID);
        }

        // 转换为实体
        CleaningAppointment cleaningAppointment = UpdateCleaningAppointmentDto.objToCleaningAppointment(updateCleaningAppointmentDto);

        // 更新
        log.info("更新保洁预约");
        return this.updateById(cleaningAppointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelCleaningAppointment(Integer id, Integer userId) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(userId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询预约
        CleaningAppointment appointment = this.getById(id);
        if (appointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查是否是用户自己的预约
        if (!appointment.getUserId().equals(userId)) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }

        // 检查状态是否可取消（待确认或已确认状态可取消）
        Integer status = appointment.getStatus();
        if (status != 0 && status != 1) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_CANNOT_CANCEL);
        }

        // 设置为已取消状态
        appointment.setStatus(3);
        
        // 更新
        log.info("用户取消保洁预约");
        return this.updateById(appointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelCleaningAppointmentByAdmin(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询预约
        CleaningAppointment appointment = this.getById(id);
        if (appointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查状态是否可取消（非已完成或已取消状态可取消）
        Integer status = appointment.getStatus();
        if (status == 2 || status == 3) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_CANNOT_CANCEL);
        }

        // 设置为已取消状态
        appointment.setStatus(3);
        
        // 更新
        log.info("管理员取消保洁预约");
        return this.updateById(appointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean confirmCleaningAppointment(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询预约
        CleaningAppointment appointment = this.getById(id);
        if (appointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查状态是否为待确认
        if (appointment.getStatus() != 0) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_CANNOT_CANCEL);
        }

        // 设置为已确认状态
        appointment.setStatus(1);
        
        // 更新
        log.info("管理员确认保洁预约");
        return this.updateById(appointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rejectCleaningAppointment(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询预约
        CleaningAppointment appointment = this.getById(id);
        if (appointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查状态是否为待确认
        if (appointment.getStatus() != 0) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_CANNOT_CANCEL);
        }

        // 设置为已拒绝状态
        appointment.setStatus(-1);
        
        // 更新
        log.info("管理员拒绝保洁预约");
        return this.updateById(appointment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean completeCleaningAppointment(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询预约
        CleaningAppointment appointment = this.getById(id);
        if (appointment == null) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_NOT_EXIST);
        }

        // 检查状态是否为已确认
        if (appointment.getStatus() != 1) {
            throw new BusinessException(ErrorType.CLEANING_APPOINTMENT_CANNOT_CANCEL);
        }

        // 设置为已完成状态
        appointment.setStatus(2);
        
        // 设置支付状态为已支付
        appointment.setPaymentStatus(1);
        
        // 更新
        log.info("管理员完成保洁预约");
        return this.updateById(appointment);
    }

    @Override
    public List<CleaningAppointmentVO> getCleaningAppointmentsByUserId(Integer userId) {
        // 判空
        Optional.ofNullable(userId)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询用户的所有预约
        LambdaQueryWrapper<CleaningAppointment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CleaningAppointment::getUserId, userId);
        queryWrapper.orderByDesc(CleaningAppointment::getCreateTime);
        
        List<CleaningAppointment> appointments = this.list(queryWrapper);
        
        // 转换为VO并返回
        return convertToVOList(appointments);
    }

    /**
     * 将实体列表转换为VO列表，并且填充服务名称
     */
    private List<CleaningAppointmentVO> convertToVOList(List<CleaningAppointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为VO
        List<CleaningAppointmentVO> voList = appointments.stream()
                .map(CleaningAppointmentVO::entityToVO)
                .collect(Collectors.toList());

        // 获取所有服务ID
        List<Integer> serviceIds = appointments.stream()
                .map(CleaningAppointment::getServiceId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        if (!serviceIds.isEmpty()) {
            // 查询所有相关的服务
            List<CleaningService> services = cleaningServiceMapper.selectBatchIds(serviceIds);

            // 构建服务ID到服务名称的映射
            Map<Integer, String> serviceMap = services.stream()
                    .collect(Collectors.toMap(CleaningService::getId, CleaningService::getName));

            // 填充服务名称
            for (CleaningAppointmentVO vo : voList) {
                if (vo.getServiceId() != null) {
                    vo.setServiceName(serviceMap.getOrDefault(vo.getServiceId(), "未知服务"));
                }
            }
        }

        return voList;
    }

    /**
     * 填充单个VO的服务名称
     */
    private CleaningAppointmentVO enrichCleaningAppointmentVO(CleaningAppointmentVO vo) {
        if (vo == null || vo.getServiceId() == null) {
            return vo;
        }

        // 查询服务
        CleaningService service = cleaningServiceMapper.selectById(vo.getServiceId());
        if (service != null) {
            vo.setServiceName(service.getName());
        } else {
            vo.setServiceName("未知服务");
        }

        return vo;
    }
} 