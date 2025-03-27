package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.dto.cost.AddCostTypeDto;
import com.qcx.property.domain.dto.cost.UpdateCostTypeDto;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.CostType;
import com.qcx.property.domain.entity.Room;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.RoomService;
import com.qcx.property.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
* @author yanqing
* @description 针对表【room(房间表)】的数据库操作Service实现
* @createDate 2025-03-27 11:33:54
*/
@Slf4j
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room>
    implements RoomService{

    @Override
    public Page<Room> getAllRooms(QueryRoomDto queryRoomDto) {
        // 判空
        if (queryRoomDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Integer id = queryRoomDto.getId();
        String name = queryRoomDto.getName();
        Integer num = queryRoomDto.getNum();
        Integer tenant = queryRoomDto.getTenant();
        Integer owner = queryRoomDto.getOwner();
        BigDecimal price = queryRoomDto.getPrice();
        Integer status = queryRoomDto.getStatus();
        String description = queryRoomDto.getDescription();


        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.eq(num!= null, "num", num);
        queryWrapper.eq(tenant!= null, "tenant", tenant);
        queryWrapper.eq(owner!= null, "owner", owner);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有房间");

        return this.page(new Page<>(queryRoomDto.getCurrent(), queryRoomDto.getPageSize()), queryWrapper);
    }

    @Override
    public boolean updateRoom(UpdateRoomDto updateRoomDto) {
        // 判空
        Optional.ofNullable(updateRoomDto.getId())
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 有效性判断
        Integer updateRoomDtoId = updateRoomDto.getId();
        Optional.ofNullable(this.getById(updateRoomDtoId))
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_EXIST));

        Room updateRoom = UpdateRoomDto.objToRoom(updateRoomDto);

        boolean updateResult = this.updateById(updateRoom);
        log.info("更新房间信息");

        return updateResult;
    }

    @Override
    public boolean addRoom(AddRoomDto addRoomDto) {
        // 判空
        Optional.ofNullable(addRoomDto)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        // 房间号不能重复
        Room isExistRoom = this.getOne(new QueryWrapper<Room>().eq("num", addRoomDto.getNum()));
        if (isExistRoom != null) {
            throw new BusinessException(ErrorType.ROOM_ALREADY_EXIST);
        }

        // 添加房间信息
        Room addRoom = AddRoomDto.objToRoom(addRoomDto);
        boolean saveResult = this.save(addRoom);
        log.info("新增房间信息");

        return saveResult;
    }

    @Override
    public boolean deleteRoom(Integer id) {
        Optional.ofNullable(id)
                .orElseThrow(() -> new BusinessException(ErrorType.ARGS_NOT_NULL));

        Optional.ofNullable(this.getById(id))
                .orElseThrow(() -> new BusinessException(ErrorType.ROOM_NOT_EXIST));

        boolean deleteResult = this.removeById(id);
        log.info("删除房间信息 id：{}", id);

        return deleteResult;
    }

    @Override
    public boolean deleteBatchRoom(Integer... roomIds) {
        // 判空
        if (roomIds == null || roomIds.length == 0) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        // 有效性判断
        List<Room> roomList = this.listByIds(Arrays.asList(roomIds));
        if (roomList.size()!= roomIds.length) {
            throw new BusinessException(ErrorType.ROOM_NOT_EXIST);
        }

        // 批量删除
        int deleteResult = this.baseMapper.deleteBatchIds(Arrays.asList(roomIds));
        log.info("批量删除房间信息");

        return deleteResult > 0;
    }
}




