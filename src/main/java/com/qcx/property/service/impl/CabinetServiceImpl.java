package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.cost.AddCostTypeDto;
import com.qcx.property.domain.dto.takeouts.AddCabinetDto;
import com.qcx.property.domain.dto.takeouts.QueryCabinetDto;
import com.qcx.property.domain.dto.takeouts.UpdateCabinetDto;
import com.qcx.property.domain.entity.Cabinet;
import com.qcx.property.domain.entity.CostType;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.CabinetService;
import com.qcx.property.mapper.CabinetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author 67121
* @description 针对表【cabinet(外卖柜表)】的数据库操作Service实现
* @createDate 2025-02-17 12:17:14
*/
@Slf4j
@Service
public class CabinetServiceImpl extends ServiceImpl<CabinetMapper, Cabinet>
    implements CabinetService{


    @Override
    public Page<Cabinet> getAllCabinets(QueryCabinetDto queryCabinetDto) {
        // 判空
        Optional.ofNullable(queryCabinetDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Integer id = queryCabinetDto.getId();
        String code = queryCabinetDto.getCode();
        Integer status = queryCabinetDto.getStatus();
        Integer type = queryCabinetDto.getType();
        String description = queryCabinetDto.getDescription();


        QueryWrapper<Cabinet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.eq(type!= type, "type", type);
        queryWrapper.like(code!= null, "code", code);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有外卖柜");

        return this.page(new Page<>(queryCabinetDto.getCurrent(), queryCabinetDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateCabinet(UpdateCabinetDto updateCabinetDto) {
        // 判空
        Optional.ofNullable(updateCabinetDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateCabinetId = updateCabinetDto.getId();
        Optional.ofNullable(this.getById(updateCabinetId))
                .orElseThrow(() -> new BusinessException(ErrorType.CABINET_NOT_EXIST));

        Cabinet updateCabinet = UpdateCabinetDto.objToCabinet(updateCabinetDto);

        boolean updateResult = this.updateById(updateCabinet);
        log.info("更新外卖柜");

        return updateResult;
    }

    @Override
    public boolean addCabinet(AddCabinetDto addCabinetDto) {
        // 判空
        Optional.ofNullable(addCabinetDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 外卖柜编码不能重复
        Cabinet isExistCabinet = this.getOne(new QueryWrapper<Cabinet>().eq("code", addCabinetDto.getCode()));
        if (isExistCabinet != null) {
            throw new BusinessException(ErrorType.CABINET_CODE_REPEAT);
        }

        // 添加外卖柜
        Cabinet addCabinet = AddCabinetDto.objToCabinet(addCabinetDto);
        boolean saveResult = this.save(addCabinet);
        log.info("新增外卖柜");

        return saveResult;
    }

    @Override
    public boolean deleteCabinet(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.CABINET_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除外卖柜 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchCabinet(Integer... cabinetIds) {
        // 判空
        if (cabinetIds == null || cabinetIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Cabinet> cabinetList = this.listByIds(Arrays.asList(cabinetIds));
        if (cabinetList.size()!= cabinetIds.length) {
            throw new BusinessException(ErrorType.CABINET_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(cabinetIds));
        log.info("批量删除外卖柜");

        return deleteResult > 0;
    }
}




