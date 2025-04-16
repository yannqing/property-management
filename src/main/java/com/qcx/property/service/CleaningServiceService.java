package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.cleaning.AddCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningServiceDto;
import com.qcx.property.domain.entity.CleaningService;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.CleaningServiceVO;

import java.util.List;

/**
* @author yannqing
* @description 针对表【cleaning_service】的数据库操作Service
* @createDate 2025-02-09 11:00:02
*/
public interface CleaningServiceService extends IService<CleaningService> {

    /**
     * 查询所有保洁服务
     * @param queryCleaningServiceDto 查询条件
     * @return 分页结果
     */
    Page<CleaningServiceVO> getAllCleaningServices(QueryCleaningServiceDto queryCleaningServiceDto);

    /**
     * 根据id查询保洁服务
     * @param id 保洁服务id
     * @return 保洁服务信息
     */
    CleaningServiceVO getCleaningServiceById(Integer id);

    /**
     * 添加保洁服务
     * @param addCleaningServiceDto 保洁服务信息
     * @return 是否成功
     */
    boolean addCleaningService(AddCleaningServiceDto addCleaningServiceDto);

    /**
     * 更新保洁服务
     * @param updateCleaningServiceDto 保洁服务信息
     * @return 是否成功
     */
    boolean updateCleaningService(UpdateCleaningServiceDto updateCleaningServiceDto);

    /**
     * 删除保洁服务
     * @param id 保洁服务id
     * @return 是否成功
     */
    boolean deleteCleaningService(Integer id);

    /**
     * 批量删除保洁服务
     * @param ids 保洁服务id数组
     * @return 是否成功
     */
    boolean batchDeleteCleaningService(Integer... ids);

    /**
     * 获取所有可用的保洁服务
     * @return 所有可用的保洁服务列表
     */
    List<CleaningServiceVO> getAllEnabledCleaningServices();
} 