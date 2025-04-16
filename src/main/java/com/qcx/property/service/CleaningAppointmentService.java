package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.cleaning.AddCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningAppointmentDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningAppointmentDto;
import com.qcx.property.domain.entity.CleaningAppointment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.CleaningAppointmentVO;

import java.util.List;

/**
* @author yannqing
* @description 针对表【cleaning_appointment】的数据库操作Service
* @createDate 2025-02-09 11:10:02
*/
public interface CleaningAppointmentService extends IService<CleaningAppointment> {

    /**
     * 查询保洁预约
     * @param queryCleaningAppointmentDto 查询条件
     * @return 分页结果
     */
    Page<CleaningAppointmentVO> getCleaningAppointments(QueryCleaningAppointmentDto queryCleaningAppointmentDto);

    /**
     * 根据id查询保洁预约
     * @param id 预约id
     * @return 预约信息
     */
    CleaningAppointmentVO getCleaningAppointmentById(Integer id);

    /**
     * 添加保洁预约
     * @param addCleaningAppointmentDto 预约信息
     * @return 是否成功
     */
    boolean addCleaningAppointment(AddCleaningAppointmentDto addCleaningAppointmentDto);

    /**
     * 更新保洁预约
     * @param updateCleaningAppointmentDto 预约信息
     * @return 是否成功
     */
    boolean updateCleaningAppointment(UpdateCleaningAppointmentDto updateCleaningAppointmentDto);

    /**
     * 用户取消保洁预约
     * @param id 预约id
     * @param userId 用户id
     * @return 是否成功
     */
    boolean cancelCleaningAppointment(Integer id, Integer userId);

    /**
     * 管理员取消保洁预约
     * @param id 预约id
     * @return 是否成功
     */
    boolean cancelCleaningAppointmentByAdmin(Integer id);
    
    /**
     * 管理员确认保洁预约
     * @param id 预约id
     * @return 是否成功
     */
    boolean confirmCleaningAppointment(Integer id);
    
    /**
     * 管理员拒绝保洁预约
     * @param id 预约id
     * @return 是否成功
     */
    boolean rejectCleaningAppointment(Integer id);
    
    /**
     * 管理员完成保洁预约
     * @param id 预约id
     * @return 是否成功
     */
    boolean completeCleaningAppointment(Integer id);
    
    /**
     * 根据用户id查询其保洁预约列表
     * @param userId 用户id
     * @return 预约列表
     */
    List<CleaningAppointmentVO> getCleaningAppointmentsByUserId(Integer userId);
} 