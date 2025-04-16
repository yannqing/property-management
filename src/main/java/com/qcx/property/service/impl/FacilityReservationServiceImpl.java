package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.reservation.AddFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.QueryFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.UpdateFacilityReservationDTO;
import com.qcx.property.domain.entity.FacilityReservation;
import com.qcx.property.domain.entity.PublicFacility;
import com.qcx.property.domain.vo.reservation.FacilityReservationVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.FacilityReservationMapper;
import com.qcx.property.service.FacilityReservationService;
import com.qcx.property.service.PublicFacilityService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 设施预约服务实现类
 */
@Service
public class FacilityReservationServiceImpl extends ServiceImpl<FacilityReservationMapper, FacilityReservation> implements FacilityReservationService {

    @Resource
    private FacilityReservationMapper facilityReservationMapper;
    
    @Resource
    private PublicFacilityService publicFacilityService;
    
    @Override
    public Page<FacilityReservationVO> getAllFacilityReservations(QueryFacilityReservationDTO queryFacilityReservationDTO) {
        // 构建查询条件
        LambdaQueryWrapper<FacilityReservation> wrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (queryFacilityReservationDTO.getFacilityId() != null) {
            wrapper.eq(FacilityReservation::getFacilityId, queryFacilityReservationDTO.getFacilityId());
        }
        
        if (queryFacilityReservationDTO.getUserId() != null) {
            wrapper.eq(FacilityReservation::getUserId, queryFacilityReservationDTO.getUserId());
        }
        
        if (queryFacilityReservationDTO.getStatus() != null) {
            wrapper.eq(FacilityReservation::getStatus, queryFacilityReservationDTO.getStatus());
        }
        
        if (queryFacilityReservationDTO.getStartDate() != null) {
            wrapper.ge(FacilityReservation::getStartTime, queryFacilityReservationDTO.getStartDate());
        }
        
        if (queryFacilityReservationDTO.getEndDate() != null) {
            wrapper.le(FacilityReservation::getEndTime, queryFacilityReservationDTO.getEndDate());
        }
        
        // 分页查询
        Page<FacilityReservation> page = new Page<>(queryFacilityReservationDTO.getPageNum(), queryFacilityReservationDTO.getPageSize());
        Page<FacilityReservation> reservationPage = page(page, wrapper);
        
        // 转换为VO对象
        Page<FacilityReservationVO> resultPage = new Page<>();
        BeanUtils.copyProperties(reservationPage, resultPage, "records");
        
        // 收集设施ID
        Set<Integer> facilityIds = new HashSet<>();
        reservationPage.getRecords().forEach(reservation -> facilityIds.add(reservation.getFacilityId()));
        
        // 批量获取设施信息
        Map<Integer, PublicFacility> facilityMap = new HashMap<>();
        if (!facilityIds.isEmpty()) {
            List<PublicFacility> facilities = publicFacilityService.listByIds(facilityIds);
            facilities.forEach(facility -> facilityMap.put(facility.getId(), facility));
        }
        
        // 转换预约记录
        List<FacilityReservationVO> voList = new ArrayList<>();
        for (FacilityReservation reservation : reservationPage.getRecords()) {
            FacilityReservationVO vo = new FacilityReservationVO();
            BeanUtils.copyProperties(reservation, vo);
            
            // 设置设施信息
            PublicFacility facility = facilityMap.get(reservation.getFacilityId());
            if (facility != null) {
                vo.setFacilityName(facility.getName());
                vo.setFacilityType(facility.getType());
                // 根据类型设置类型名称
                switch (facility.getType()) {
                    case 1:
                        vo.setFacilityTypeName("健身设施");
                        break;
                    case 2:
                        vo.setFacilityTypeName("娱乐设施");
                        break;
                    case 3:
                        vo.setFacilityTypeName("会议室");
                        break;
                    case 4:
                        vo.setFacilityTypeName("活动室");
                        break;
                    case 5:
                    default:
                        vo.setFacilityTypeName("其他");
                        break;
                }
                vo.setFacilityLocation(facility.getLocation());
            }
            
            // 设置状态描述
            switch (reservation.getStatus()) {
                case 0:
                    vo.setStatusDesc("待审核");
                    break;
                case 1:
                    vo.setStatusDesc("已通过");
                    break;
                case 2:
                    vo.setStatusDesc("已拒绝");
                    break;
                case 3:
                    vo.setStatusDesc("已取消");
                    break;
                case 4:
                    vo.setStatusDesc("已完成");
                    break;
                default:
                    vo.setStatusDesc("未知状态");
                    break;
            }
            
            voList.add(vo);
        }
        
        resultPage.setRecords(voList);
        return resultPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addFacilityReservation(AddFacilityReservationDTO addFacilityReservationDTO) {
        // 检查设施是否存在
        PublicFacility facility = publicFacilityService.getById(addFacilityReservationDTO.getFacilityId());
        if (facility == null) {
            throw new BusinessException(ErrorType.PUBLIC_FACILITY_NOT_EXIST);
        }
        
        // 检查设施是否可用
        if (facility.getStatus() == 0) {
            throw new BusinessException(ErrorType.PUBLIC_FACILITY_DISABLED);
        }
        
        // 检查预约人数是否超过容量
        if (addFacilityReservationDTO.getPersons() > facility.getCapacity()) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_CAPACITY_EXCEED);
        }
        
        // 检查预约时间是否冲突
        checkTimeConflict(addFacilityReservationDTO.getFacilityId(), addFacilityReservationDTO.getStartTime(), 
                addFacilityReservationDTO.getEndTime(), null);
        
        // 创建预约记录
        FacilityReservation reservation = new FacilityReservation();
        BeanUtils.copyProperties(addFacilityReservationDTO, reservation);
        
        // 确保userId被设置
        if (reservation.getUserId() == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        
        // 设置状态为待审核
        reservation.setStatus(0);
        
        // 设置创建时间和更新时间
        Date now = new Date();
        reservation.setCreateTime(now);
        reservation.setUpdateTime(now);
        reservation.setIsDelete(0);
        
        return save(reservation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateFacilityReservation(UpdateFacilityReservationDTO updateFacilityReservationDTO) {
        // 检查预约是否存在
        FacilityReservation reservation = getById(updateFacilityReservationDTO.getId());
        if (reservation == null) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_NOT_EXIST);
        }
        
        // 如果更改了开始时间或结束时间，需要检查时间冲突
        if (updateFacilityReservationDTO.getStartTime() != null || updateFacilityReservationDTO.getEndTime() != null) {
            Date startTime = updateFacilityReservationDTO.getStartTime() != null ? 
                    updateFacilityReservationDTO.getStartTime() : reservation.getStartTime();
            Date endTime = updateFacilityReservationDTO.getEndTime() != null ? 
                    updateFacilityReservationDTO.getEndTime() : reservation.getEndTime();
            
            checkTimeConflict(reservation.getFacilityId(), startTime, endTime, reservation.getId());
        }
        
        // 复制属性
        BeanUtils.copyProperties(updateFacilityReservationDTO, reservation);
        
        // 设置更新时间
        reservation.setUpdateTime(new Date());
        
        return updateById(reservation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteFacilityReservation(Integer id) {
        // 检查预约是否存在
        FacilityReservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_NOT_EXIST);
        }
        
        // 逻辑删除
        return removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteFacilityReservation(Integer[] ids) {
        return removeBatchByIds(Arrays.asList(ids));
    }

    @Override
    public FacilityReservationVO getFacilityReservationDetail(Integer id) {
        // 检查预约是否存在
        FacilityReservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_NOT_EXIST);
        }
        
        // 转换为VO对象
        FacilityReservationVO vo = new FacilityReservationVO();
        BeanUtils.copyProperties(reservation, vo);
        
        // 获取设施信息
        PublicFacility facility = publicFacilityService.getById(reservation.getFacilityId());
        if (facility != null) {
            vo.setFacilityName(facility.getName());
            vo.setFacilityType(facility.getType());
            // 根据类型设置类型名称
            switch (facility.getType()) {
                case 1:
                    vo.setFacilityTypeName("健身设施");
                    break;
                case 2:
                    vo.setFacilityTypeName("娱乐设施");
                    break;
                case 3:
                    vo.setFacilityTypeName("会议室");
                    break;
                case 4:
                    vo.setFacilityTypeName("活动室");
                    break;
                case 5:
                default:
                    vo.setFacilityTypeName("其他");
                    break;
            }
            vo.setFacilityLocation(facility.getLocation());
        }
        
        // 设置状态描述
        switch (reservation.getStatus()) {
            case 0:
                vo.setStatusDesc("待审核");
                break;
            case 1:
                vo.setStatusDesc("已通过");
                break;
            case 2:
                vo.setStatusDesc("已拒绝");
                break;
            case 3:
                vo.setStatusDesc("已取消");
                break;
            case 4:
                vo.setStatusDesc("已完成");
                break;
            default:
                vo.setStatusDesc("未知状态");
                break;
        }
        
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelFacilityReservation(Integer id) {
        // 检查预约是否存在
        FacilityReservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_NOT_EXIST);
        }
        
        // 检查预约状态是否可取消
        if (reservation.getStatus() != 0 && reservation.getStatus() != 1) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_CANNOT_CANCEL);
        }
        
        // 设置状态为已取消
        reservation.setStatus(3);
        reservation.setUpdateTime(new Date());
        
        return updateById(reservation);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditFacilityReservation(Integer id, Integer status, String remarks) {
        // 检查预约是否存在
        FacilityReservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_NOT_EXIST);
        }
        
        // 检查预约状态是否为待审核
        if (reservation.getStatus() != 0) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_CANNOT_CANCEL);
        }
        
        // 设置审核结果
        reservation.setStatus(status);
        reservation.setRemarks(remarks);
        reservation.setUpdateTime(new Date());
        
        return updateById(reservation);
    }
    
    /**
     * 检查设施预约时间是否冲突
     * @param facilityId 设施ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param excludeId 排除的预约ID，用于更新时排除自身
     */
    private void checkTimeConflict(Integer facilityId, Date startTime, Date endTime, Integer excludeId) {
        // 验证时间是否有效
        if (startTime == null || endTime == null || startTime.after(endTime)) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_TIME_INVALID);
        }
        
        // 查询设施在指定时间段内的预约
        LambdaQueryWrapper<FacilityReservation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FacilityReservation::getFacilityId, facilityId)
               .ne(FacilityReservation::getStatus, 2) // 不等于已拒绝
               .ne(FacilityReservation::getStatus, 3) // 不等于已取消
               .and(w -> w.between(FacilityReservation::getStartTime, startTime, endTime)
                       .or()
                       .between(FacilityReservation::getEndTime, startTime, endTime)
                       .or()
                       .nested(n -> n.le(FacilityReservation::getStartTime, startTime)
                               .ge(FacilityReservation::getEndTime, endTime)));
        
        // 排除自身
        if (excludeId != null) {
            wrapper.ne(FacilityReservation::getId, excludeId);
        }
        
        long count = count(wrapper);
        if (count > 0) {
            throw new BusinessException(ErrorType.FACILITY_RESERVATION_CONFLICT);
        }
    }
} 