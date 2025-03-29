package com.qcx.property.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.Room;
import com.qcx.property.domain.entity.User;
import com.qcx.property.domain.model.RoomModel;
import com.qcx.property.domain.vo.room.RoomVo;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.RoomService;
import com.qcx.property.mapper.RoomMapper;
import com.qcx.property.service.UserService;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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

    @Resource
    private UserService userService;

    @Override
    public Page<RoomVo> getAllRooms(QueryRoomDto queryRoomDto) {
        // 判空
        if (queryRoomDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        Integer id = queryRoomDto.getId();
        String name = queryRoomDto.getName();
        String num = queryRoomDto.getNum();
        Integer tenant = queryRoomDto.getTenant();
        Integer owner = queryRoomDto.getOwner();
        BigDecimal price = queryRoomDto.getPrice();
        Integer status = queryRoomDto.getStatus();
        String description = queryRoomDto.getDescription();


        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(num!= null, "num", num);
        queryWrapper.eq(tenant!= null, "tenant", tenant);
        queryWrapper.eq(owner!= null, "owner", owner);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有房间");
        Page<Room> page = this.page(new Page<>(queryRoomDto.getCurrent(), queryRoomDto.getPageSize()), queryWrapper);
        List<RoomVo> roomVoList = page.getRecords().stream().map(room -> {
            RoomVo roomVo = RoomVo.convert(room);
            roomVo.setRoomModel(JSON.parseObject(room.getDescription(), RoomModel.class));
            return roomVo;
        }).toList();

        return new Page<RoomVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(roomVoList);
    }

    @Override
    public Page<RoomVo> getAllRoomsByOwner(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        if (queryRoomDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));

        Integer id = queryRoomDto.getId();
        String name = queryRoomDto.getName();
        String num = queryRoomDto.getNum();
        Integer tenant = queryRoomDto.getTenant();
        // 查询条件：业主 = 登录用户 id
        Integer owner = loginUser.getUserId();
        BigDecimal price = queryRoomDto.getPrice();
        Integer status = queryRoomDto.getStatus();
        String description = queryRoomDto.getDescription();


        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(num!= null, "num", num);
        queryWrapper.eq(tenant!= null, "tenant", tenant);
        queryWrapper.eq(owner!= null, "owner", owner);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有房间");
        Page<Room> page = this.page(new Page<>(queryRoomDto.getCurrent(), queryRoomDto.getPageSize()), queryWrapper);
        List<RoomVo> roomVoList = page.getRecords().stream().map(room -> {
            RoomVo roomVo = RoomVo.convert(room);
            roomVo.setRoomModel(JSON.parseObject(room.getDescription(), RoomModel.class));
            return roomVo;
        }).toList();

        return new Page<RoomVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(roomVoList);
    }

    @Override
    public Page<RoomVo> getAllRoomsByUser(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException {
        // 判空
        if (queryRoomDto == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }

        User loginUser = JwtUtils.getUserFromToken(request.getHeader("token"));

        Integer id = queryRoomDto.getId();
        String name = queryRoomDto.getName();
        String num = queryRoomDto.getNum();
        // 查询条件，租客 = 登录用户 id
        Integer tenant = loginUser.getUserId();
        Integer owner = queryRoomDto.getOwner();
        BigDecimal price = queryRoomDto.getPrice();
        Integer status = queryRoomDto.getStatus();
        String description = queryRoomDto.getDescription();


        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id!= null, "id", id);
        queryWrapper.like(name!= null, "name", name);
        queryWrapper.like(num!= null, "num", num);
        queryWrapper.eq(tenant!= null, "tenant", tenant);
        queryWrapper.eq(owner!= null, "owner", owner);
        queryWrapper.eq(price!= null, "price", price);
        queryWrapper.eq(status!= null, "status", status);
        queryWrapper.like(description!= null, "description", description);
        log.info("查询所有房间");
        Page<Room> page = this.page(new Page<>(queryRoomDto.getCurrent(), queryRoomDto.getPageSize()), queryWrapper);
        List<RoomVo> roomVoList = page.getRecords().stream().map(room -> {
            RoomVo roomVo = RoomVo.convert(room);
            roomVo.setRoomModel(JSON.parseObject(room.getDescription(), RoomModel.class));
            return roomVo;
        }).toList();

        return new Page<RoomVo>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(roomVoList);
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
        updateRoom.setDescription(JSON.toJSONString(updateRoomDto.getRoomModel()));

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
        addRoom.setDescription(JSON.toJSONString(addRoomDto.getRoomModel()));
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

    @Override
    public boolean addUserToRoom(Integer roomId, Integer userId, BigDecimal price) {
        if (roomId == null || userId == null || price == null) {
            throw new BusinessException(ErrorType.ARGS_NOT_NULL);
        }
        Room room = this.getById(roomId);
        if (room == null) {
            throw new BusinessException(ErrorType.ROOM_NOT_EXIST);
        }
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorType.USER_NOT_EXIST);
        }

        // 新增租客
        room.setTenant(userId);
        room.setPrice(price);
        boolean result = this.updateById(room);
        log.info("添加租客id：{}，到房间id：{}", userId, roomId);
        return result;
    }
}




