package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.committee.AddOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.QueryOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.UpdateOwnersCommitteeDto;
import com.qcx.property.domain.entity.OwnersCommittee;
import com.qcx.property.domain.vo.OwnersCommitteeVO;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.mapper.OwnersCommitteeMapper;
import com.qcx.property.service.OwnersCommitteeService;
import com.qcx.property.utils.BeanCopyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 业主委员会服务实现类
 * @author: yannqing
 * @create: 2025-04-16 15:10
 * @from: <更多资料：yannqing.com>
 **/
@Service
public class OwnersCommitteeServiceImpl extends ServiceImpl<OwnersCommitteeMapper, OwnersCommittee> implements OwnersCommitteeService {


    @Override
    public Page<OwnersCommitteeVO> pageOwnersCommittee(QueryOwnersCommitteeDto queryOwnersCommitteeDto) {
        // 构建查询条件
        LambdaQueryWrapper<OwnersCommittee> queryWrapper = new LambdaQueryWrapper<>();
        
        // 添加名称模糊查询条件
        if (StringUtils.isNotBlank(queryOwnersCommitteeDto.getName())) {
            queryWrapper.like(OwnersCommittee::getName, queryOwnersCommitteeDto.getName());
        }
        
        // 添加状态查询条件
        if (Objects.nonNull(queryOwnersCommitteeDto.getStatus())) {
            queryWrapper.eq(OwnersCommittee::getStatus, queryOwnersCommitteeDto.getStatus());
        }
        
        // 添加成立日期范围查询条件
        if (Objects.nonNull(queryOwnersCommitteeDto.getEstablishmentDateStart()) && 
            Objects.nonNull(queryOwnersCommitteeDto.getEstablishmentDateEnd())) {
            queryWrapper.between(OwnersCommittee::getEstablishmentDate, 
                    queryOwnersCommitteeDto.getEstablishmentDateStart(),
                    queryOwnersCommitteeDto.getEstablishmentDateEnd());
        }
        
        // 排序
        queryWrapper.orderByDesc(OwnersCommittee::getCreateTime);
        
        // 分页查询
        Page<OwnersCommittee> page = new Page<>(queryOwnersCommitteeDto.getCurrent(), queryOwnersCommitteeDto.getPageSize());
        Page<OwnersCommittee> ownersCommitteePage = this.page(page, queryWrapper);
        
        // 转换为VO
        Page<OwnersCommitteeVO> resultPage = new Page<>(
                ownersCommitteePage.getCurrent(),
                ownersCommitteePage.getSize(),
                ownersCommitteePage.getTotal()
        );
        
        List<OwnersCommitteeVO> voList = ownersCommitteePage.getRecords().stream()
                .map(committee -> BeanCopyUtils.copyBean(committee, OwnersCommitteeVO.class))
                .collect(Collectors.toList());
        
        resultPage.setRecords(voList);
        return resultPage;
    }

    @Override
    public OwnersCommitteeVO getOwnersCommitteeById(Integer id) {
        if (Objects.isNull(id)) {
            throw new BusinessException("委员会ID不能为空");
        }
        
        OwnersCommittee ownersCommittee = this.getById(id);
        if (Objects.isNull(ownersCommittee)) {
            throw new BusinessException("委员会不存在");
        }
        
        return BeanCopyUtils.copyBean(ownersCommittee, OwnersCommitteeVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOwnersCommittee(AddOwnersCommitteeDto addOwnersCommitteeDto) {
        // 验证名称是否已存在
        LambdaQueryWrapper<OwnersCommittee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OwnersCommittee::getName, addOwnersCommitteeDto.getName());
        long count = this.count(queryWrapper);
        if (count > 0) {
            throw new BusinessException("委员会名称已存在");
        }
        
        // DTO转换为实体
        OwnersCommittee ownersCommittee = BeanCopyUtils.copyBean(addOwnersCommitteeDto, OwnersCommittee.class);
        
        // 设置默认值
        ownersCommittee.setCreateTime(new Date());
        ownersCommittee.setUpdateTime(new Date());
        ownersCommittee.setIsDelete(0);
        
        // 保存
        return this.save(ownersCommittee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOwnersCommittee(UpdateOwnersCommitteeDto updateOwnersCommitteeDto) {
        // 验证ID是否存在
        if (Objects.isNull(updateOwnersCommitteeDto.getId())) {
            throw new BusinessException("委员会ID不能为空");
        }
        
        OwnersCommittee existsCommittee = this.getById(updateOwnersCommitteeDto.getId());
        if (Objects.isNull(existsCommittee)) {
            throw new BusinessException("委员会不存在");
        }
        
        // 验证名称是否重复
        if (StringUtils.isNotBlank(updateOwnersCommitteeDto.getName()) 
                && !updateOwnersCommitteeDto.getName().equals(existsCommittee.getName())) {
            LambdaQueryWrapper<OwnersCommittee> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(OwnersCommittee::getName, updateOwnersCommitteeDto.getName());
            long count = this.count(queryWrapper);
            if (count > 0) {
                throw new BusinessException("委员会名称已存在");
            }
        }
        
        // DTO转换为实体
        OwnersCommittee ownersCommittee = BeanCopyUtils.copyBean(updateOwnersCommitteeDto, OwnersCommittee.class);
        ownersCommittee.setUpdateTime(new Date());
        
        // 更新
        return this.updateById(ownersCommittee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOwnersCommittee(Integer id) {
        if (Objects.isNull(id)) {
            throw new BusinessException("委员会ID不能为空");
        }
        
        // 可以在此处添加删除前的业务检查
        // 例如：检查是否存在关联的委员会成员或会议
        
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteOwnersCommittee(Integer[] ids) {
        if (ObjectUtils.isEmpty(ids)) {
            throw new BusinessException("委员会ID不能为空");
        }
        
        // 可以在此处添加删除前的业务检查
        // 例如：检查是否存在关联的委员会成员或会议
        
        List<Integer> idList = Arrays.asList(ids);
        return this.removeByIds(idList);
    }
} 