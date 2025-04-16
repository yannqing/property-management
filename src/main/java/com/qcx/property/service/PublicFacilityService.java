package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.dto.facility.AddPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.QueryPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.UpdatePublicFacilityDTO;
import com.qcx.property.domain.entity.PublicFacility;
import com.qcx.property.domain.vo.PublicFacilityVO;

/**
 * 公共设施服务接口
 */
public interface PublicFacilityService extends IService<PublicFacility> {

    /**
     * 查询所有公共设施
     * @param queryPublicFacilityDTO 查询条件
     * @return 公共设施分页列表
     */
    Page<PublicFacilityVO> getAllPublicFacilities(QueryPublicFacilityDTO queryPublicFacilityDTO);

    /**
     * 添加公共设施
     * @param addPublicFacilityDTO 添加公共设施DTO
     * @return 是否添加成功
     */
    boolean addPublicFacility(AddPublicFacilityDTO addPublicFacilityDTO);

    /**
     * 更新公共设施
     * @param updatePublicFacilityDTO 更新公共设施DTO
     * @return 是否更新成功
     */
    boolean updatePublicFacility(UpdatePublicFacilityDTO updatePublicFacilityDTO);

    /**
     * 删除公共设施
     * @param id 公共设施ID
     * @return 是否删除成功
     */
    boolean deletePublicFacility(Integer id);

    /**
     * 批量删除公共设施
     * @param ids 公共设施ID数组
     * @return 是否批量删除成功
     */
    boolean batchDeletePublicFacility(Integer[] ids);
    
    /**
     * 获取公共设施详情
     * @param id 公共设施ID
     * @return 公共设施VO
     */
    PublicFacilityVO getPublicFacilityDetail(Integer id);
} 