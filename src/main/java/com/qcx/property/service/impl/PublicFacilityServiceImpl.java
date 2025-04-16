package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.facility.AddPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.QueryPublicFacilityDTO;
import com.qcx.property.domain.dto.facility.UpdatePublicFacilityDTO;
import com.qcx.property.domain.entity.PublicFacility;
import com.qcx.property.domain.vo.PublicFacilityVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.PublicFacilityMapper;
import com.qcx.property.service.PublicFacilityService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 公共设施服务实现类
 */
@Service
public class PublicFacilityServiceImpl extends ServiceImpl<PublicFacilityMapper, PublicFacility> implements PublicFacilityService {

    @Resource
    private PublicFacilityMapper publicFacilityMapper;

    @Override
    public Page<PublicFacilityVO> getAllPublicFacilities(QueryPublicFacilityDTO queryPublicFacilityDTO) {
        // 构建查询条件
        LambdaQueryWrapper<PublicFacility> wrapper = new LambdaQueryWrapper<>();
        
        // 添加查询条件
        if (StringUtils.hasText(queryPublicFacilityDTO.getName())) {
            wrapper.like(PublicFacility::getName, queryPublicFacilityDTO.getName());
        }
        
        if (queryPublicFacilityDTO.getType() != null) {
            wrapper.eq(PublicFacility::getType, queryPublicFacilityDTO.getType());
        }
        
        if (StringUtils.hasText(queryPublicFacilityDTO.getLocation())) {
            wrapper.like(PublicFacility::getLocation, queryPublicFacilityDTO.getLocation());
        }
        
        if (queryPublicFacilityDTO.getStatus() != null) {
            wrapper.eq(PublicFacility::getStatus, queryPublicFacilityDTO.getStatus());
        }
        
        // 分页查询
        Page<PublicFacility> page = new Page<>(queryPublicFacilityDTO.getPageNum(), queryPublicFacilityDTO.getPageSize());
        Page<PublicFacility> facilityPage = page(page, wrapper);
        
        // 转换为VO对象
        Page<PublicFacilityVO> resultPage = new Page<>();
        BeanUtils.copyProperties(facilityPage, resultPage, "records");
        
        List<PublicFacilityVO> voList = new ArrayList<>();
        for (PublicFacility facility : facilityPage.getRecords()) {
            PublicFacilityVO vo = new PublicFacilityVO();
            // 手动设置id和type字段，解决类型不匹配问题
            vo.setId(facility.getId() != null ? facility.getId().longValue() : null);
            vo.setType(facility.getType() != null ? facility.getType().toString() : null);
            
            // 复制其他属性
            BeanUtils.copyProperties(facility, vo, "id", "type");
            
            // 设置可预约状态
            vo.setAvailable(facility.getStatus() == 1);
            voList.add(vo);
        }
        
        resultPage.setRecords(voList);
        return resultPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addPublicFacility(AddPublicFacilityDTO addPublicFacilityDTO) {
        PublicFacility facility = new PublicFacility();
        BeanUtils.copyProperties(addPublicFacilityDTO, facility);
        
        // 设置创建时间和更新时间
        Date now = new Date();
        facility.setCreateTime(now);
        facility.setUpdateTime(now);
        facility.setIsDelete(0);
        
        return save(facility);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePublicFacility(UpdatePublicFacilityDTO updatePublicFacilityDTO) {
        // 检查设施是否存在
        PublicFacility facility = getById(updatePublicFacilityDTO.getId());
        if (facility == null) {
            throw new BusinessException(ErrorType.PUBLIC_FACILITY_NOT_EXIST);
        }
        
        // 复制属性
        BeanUtils.copyProperties(updatePublicFacilityDTO, facility);
        
        // 设置更新时间
        facility.setUpdateTime(new Date());
        
        return updateById(facility);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePublicFacility(Integer id) {
        // 检查设施是否存在
        PublicFacility facility = getById(id);
        if (facility == null) {
            throw new BusinessException(ErrorType.PUBLIC_FACILITY_NOT_EXIST);
        }
        
        // 逻辑删除
        return removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeletePublicFacility(Integer[] ids) {
        return removeBatchByIds(Arrays.asList(ids));
    }

    @Override
    public PublicFacilityVO getPublicFacilityDetail(Integer id) {
        // 检查设施是否存在
        PublicFacility facility = getById(id);
        if (facility == null) {
            throw new BusinessException(ErrorType.PUBLIC_FACILITY_NOT_EXIST);
        }
        
        // 转换为VO对象
        PublicFacilityVO vo = new PublicFacilityVO();
        
        // 手动设置id和type字段，解决类型不匹配问题
        vo.setId(facility.getId() != null ? facility.getId().longValue() : null);
        vo.setType(facility.getType() != null ? facility.getType().toString() : null);
        
        // 复制其他属性
        BeanUtils.copyProperties(facility, vo, "id", "type");
        
        // 设置可预约状态
        vo.setAvailable(facility.getStatus() == 1);
        
        return vo;
    }
} 