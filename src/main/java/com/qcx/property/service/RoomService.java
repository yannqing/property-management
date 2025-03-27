package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author yanqing
* @description 针对表【room(房间表)】的数据库操作Service
* @createDate 2025-03-27 11:33:54
*/
public interface RoomService extends IService<Room> {

    Page<Room> getAllRooms(QueryRoomDto queryRoomDto);

    boolean updateRoom(UpdateRoomDto updateRoomDto);

    boolean addRoom(AddRoomDto addRoomDto);

    boolean deleteRoom(Integer id);

    boolean deleteBatchRoom(Integer... roomIds);
}
