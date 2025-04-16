package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.dto.committee.AddOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.QueryOwnersCommitteeDto;
import com.qcx.property.domain.dto.committee.UpdateOwnersCommitteeDto;
import com.qcx.property.domain.entity.OwnersCommittee;
import com.qcx.property.domain.vo.OwnersCommitteeVO;

/**
 * @description: 业主委员会服务接口
 * @author: yannqing
 * @create: 2025-04-16 15:02
 * @from: <更多资料：yannqing.com>
 **/
public interface OwnersCommitteeService extends IService<OwnersCommittee> {

    /**
     * 分页查询业主委员会列表
     * @param queryOwnersCommitteeDto 查询条件
     * @return 分页结果
     */
    Page<OwnersCommitteeVO> pageOwnersCommittee(QueryOwnersCommitteeDto queryOwnersCommitteeDto);

    /**
     * 根据ID查询业主委员会详情
     * @param id 委员会ID
     * @return 委员会详情
     */
    OwnersCommitteeVO getOwnersCommitteeById(Integer id);

    /**
     * 新增业主委员会
     * @param addOwnersCommitteeDto 业主委员会信息
     * @return 是否成功
     */
    boolean addOwnersCommittee(AddOwnersCommitteeDto addOwnersCommitteeDto);

    /**
     * 更新业主委员会
     * @param updateOwnersCommitteeDto 业主委员会信息
     * @return 是否成功
     */
    boolean updateOwnersCommittee(UpdateOwnersCommitteeDto updateOwnersCommitteeDto);

    /**
     * 删除业主委员会
     * @param id 委员会ID
     * @return 是否成功
     */
    boolean deleteOwnersCommittee(Integer id);

    /**
     * 批量删除业主委员会
     * @param ids 委员会ID数组
     * @return 是否成功
     */
    boolean batchDeleteOwnersCommittee(Integer[] ids);
} 