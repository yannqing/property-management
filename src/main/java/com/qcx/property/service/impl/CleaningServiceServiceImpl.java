package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.cleaning.AddCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.QueryCleaningServiceDto;
import com.qcx.property.domain.dto.cleaning.UpdateCleaningServiceDto;
import com.qcx.property.domain.entity.CleaningService;
import com.qcx.property.domain.entity.CostType;
import com.qcx.property.domain.vo.CleaningServiceVO;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.CleaningServiceMapper;
import com.qcx.property.mapper.CostTypeMapper;
import com.qcx.property.service.CleaningServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author yannqing
* @description 针对表【cleaning_service】的数据库操作Service实现
* @createDate 2025-02-09 11:15:02
*/
@Slf4j
@Service
public class CleaningServiceServiceImpl extends ServiceImpl<CleaningServiceMapper, CleaningService>
    implements CleaningServiceService{

    @Resource
    private CostTypeMapper costTypeMapper;

    @Override
    public Page<CleaningServiceVO> getAllCleaningServices(QueryCleaningServiceDto queryCleaningServiceDto) {
        // 判空
        Optional.ofNullable(queryCleaningServiceDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCleaningServiceDto.getId();
        String name = queryCleaningServiceDto.getName();
        Integer type = queryCleaningServiceDto.getType();
        Integer status = queryCleaningServiceDto.getStatus();
        BigDecimal minPrice = queryCleaningServiceDto.getMinPrice();
        BigDecimal maxPrice = queryCleaningServiceDto.getMaxPrice();

        QueryWrapper<CleaningService> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.like(name != null, "name", name);
        queryWrapper.eq(type != null, "type", type);
        queryWrapper.eq(status != null, "status", status);
        
        if (minPrice != null) {
            queryWrapper.ge("price", minPrice);
        }
        
        if (maxPrice != null) {
            queryWrapper.le("price", maxPrice);
        }
        
        log.info("查询所有保洁服务");

        // 分页查询
        Page<CleaningService> page = this.page(
                new Page<>(queryCleaningServiceDto.getCurrent(), queryCleaningServiceDto.getPageSize()),
                queryWrapper
        );
        
        // 转换为VO
        List<CleaningServiceVO> voList = convertToVOList(page.getRecords());
        
        // 构造返回结果
        Page<CleaningServiceVO> resultPage = new Page<>();
        resultPage.setCurrent(page.getCurrent());
        resultPage.setSize(page.getSize());
        resultPage.setTotal(page.getTotal());
        resultPage.setRecords(voList);
        
        return resultPage;
    }

    @Override
    public CleaningServiceVO getCleaningServiceById(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 查询
        CleaningService cleaningService = this.getById(id);
        
        // 判断是否存在
        if (cleaningService == null) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
        }
        
        // 转换为VO并返回
        return enrichCleaningServiceVO(CleaningServiceVO.entityToVO(cleaningService));
    }

    @Override
    public boolean addCleaningService(AddCleaningServiceDto addCleaningServiceDto) {
        // 判空
        Optional.ofNullable(addCleaningServiceDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 转换为实体
        CleaningService cleaningService = AddCleaningServiceDto.objToCleaningService(addCleaningServiceDto);
        
        // 默认启用状态
        if (cleaningService.getStatus() == null) {
            cleaningService.setStatus(1);
        }
        
        // 保存
        log.info("新增保洁服务");
        return this.save(cleaningService);
    }

    @Override
    public boolean updateCleaningService(UpdateCleaningServiceDto updateCleaningServiceDto) {
        // 判空
        Optional.ofNullable(updateCleaningServiceDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));
        Optional.ofNullable(updateCleaningServiceDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 检查是否存在
        Integer serviceId = updateCleaningServiceDto.getId();
        CleaningService existService = this.getById(serviceId);
        if (existService == null) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
        }

        // 转换为实体
        CleaningService cleaningService = UpdateCleaningServiceDto.objToCleaningService(updateCleaningServiceDto);
        
        // 更新
        log.info("更新保洁服务");
        return this.updateById(cleaningService);
    }

    @Override
    public boolean deleteCleaningService(Integer id) {
        // 判空
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 检查是否存在
        CleaningService existService = this.getById(id);
        if (existService == null) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
        }

        // 删除
        log.info("删除保洁服务");
        return this.removeById(id);
    }

    @Override
    public boolean batchDeleteCleaningService(Integer... ids) {
        // 判空
        if (ids == null || ids.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 检查是否都存在
        List<CleaningService> services = this.listByIds(Arrays.asList(ids));
        if (services.size() != ids.length) {
            throw new BusinessException(ErrorType.CLEANING_SERVICE_NOT_EXIST);
        }

        // 批量删除
        log.info("批量删除保洁服务");
        return this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public List<CleaningServiceVO> getAllEnabledCleaningServices() {
        // 查询所有启用状态的服务
        LambdaQueryWrapper<CleaningService> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CleaningService::getStatus, 1);
        
        List<CleaningService> services = this.list(queryWrapper);
        
        // 转换为VO并返回
        return convertToVOList(services);
    }
    
    /**
     * 将实体列表转换为VO列表，并且填充类型名称
     */
    private List<CleaningServiceVO> convertToVOList(List<CleaningService> services) {
        if (services == null || services.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 转换为VO
        List<CleaningServiceVO> voList = services.stream()
                .map(CleaningServiceVO::entityToVO)
                .collect(Collectors.toList());
        
        // 获取所有类型ID
        List<Integer> typeIds = services.stream()
                .map(CleaningService::getType)
                .filter(type -> type != null)
                .distinct()
                .collect(Collectors.toList());
        
        if (!typeIds.isEmpty()) {
            // 查询所有相关的费用类型
            List<CostType> costTypes = costTypeMapper.selectBatchIds(typeIds);
            
            // 构建类型ID到类型名称的映射
            java.util.Map<Integer, String> typeMap = costTypes.stream()
                    .collect(Collectors.toMap(CostType::getId, CostType::getName));
            
            // 填充类型名称
            for (CleaningServiceVO vo : voList) {
                if (vo.getType() != null) {
                    vo.setTypeName(typeMap.getOrDefault(vo.getType(), "未知类型"));
                }
            }
        }
        
        return voList;
    }
    
    /**
     * 填充单个VO的类型名称
     */
    private CleaningServiceVO enrichCleaningServiceVO(CleaningServiceVO vo) {
        if (vo == null || vo.getType() == null) {
            return vo;
        }
        
        // 查询费用类型
        CostType costType = costTypeMapper.selectById(vo.getType());
        if (costType != null) {
            vo.setTypeName(costType.getName());
        } else {
            vo.setTypeName("未知类型");
        }
        
        return vo;
    }
} 