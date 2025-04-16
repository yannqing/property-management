package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.dto.reservation.AddFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.QueryFacilityReservationDTO;
import com.qcx.property.domain.dto.reservation.UpdateFacilityReservationDTO;
import com.qcx.property.domain.entity.FacilityReservation;
import com.qcx.property.domain.vo.reservation.FacilityReservationVO;

/**
 * 设施预约服务接口
 */
public interface FacilityReservationService extends IService<FacilityReservation> {

    /**
     * 查询设施预约列表
     * @param queryFacilityReservationDTO 查询条件
     * @return 设施预约分页列表
     */
    Page<FacilityReservationVO> getAllFacilityReservations(QueryFacilityReservationDTO queryFacilityReservationDTO);

    /**
     * 添加设施预约
     * @param addFacilityReservationDTO 添加设施预约DTO
     * @return 是否添加成功
     */
    boolean addFacilityReservation(AddFacilityReservationDTO addFacilityReservationDTO);

    /**
     * 更新设施预约
     * @param updateFacilityReservationDTO 更新设施预约DTO
     * @return 是否更新成功
     */
    boolean updateFacilityReservation(UpdateFacilityReservationDTO updateFacilityReservationDTO);

    /**
     * 删除设施预约
     * @param id 设施预约ID
     * @return 是否删除成功
     */
    boolean deleteFacilityReservation(Integer id);

    /**
     * 批量删除设施预约
     * @param ids 设施预约ID数组
     * @return 是否批量删除成功
     */
    boolean batchDeleteFacilityReservation(Integer[] ids);
    
    /**
     * 获取设施预约详情
     * @param id 设施预约ID
     * @return 设施预约VO
     */
    FacilityReservationVO getFacilityReservationDetail(Integer id);
    
    /**
     * 取消设施预约
     * @param id 设施预约ID
     * @return 是否取消成功
     */
    boolean cancelFacilityReservation(Integer id);
    
    /**
     * 审核设施预约
     * @param id 设施预约ID
     * @param status 审核状态
     * @param remarks 备注
     * @return 是否审核成功
     */
    boolean auditFacilityReservation(Integer id, Integer status, String remarks);
} 