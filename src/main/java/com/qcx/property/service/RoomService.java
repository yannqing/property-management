package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.room.RoomVo;
import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

/**
* @author yanqing
* @description 针对表【room(房间表)】的数据库操作Service
* @createDate 2025-03-27 11:33:54
*/
public interface RoomService extends IService<Room> {

    /**
     * 管理员查询全部房间
     * @param queryRoomDto 查询 dto
     * @return 返回封装结果 vo
     */
    Page<RoomVo> getAllRooms(QueryRoomDto queryRoomDto);

    /**
     * 业主查询全部房间
     * @param queryRoomDto 查询 dto
     * @param request 会话 session
     * @return 返回 vo 封装结果
     * @throws JsonProcessingException json 解析异常
     */
    Page<RoomVo> getAllRoomsByOwner(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 租客查询全部房间
     * @param queryRoomDto 查询 dto
     * @param request 会话 session
     * @return 返回 vo 封装结果
     * @throws JsonProcessingException json 解析异常
     */
    Page<RoomVo> getAllRoomsByUser(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 管理员更新房间
     * @param updateRoomDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateRoom(UpdateRoomDto updateRoomDto);

    /**
     * 管理员新增房间
     * @param addRoomDto 新增 dto
     * @return 返回新增结果
     */
    boolean addRoom(AddRoomDto addRoomDto);

    /**
     * 管理员删除单个房间
     * @param id 要删除的房间 id
     * @return 返回删除结果
     */
    boolean deleteRoom(Integer id);

    /**
     * 管理员批量删除房间
     * @param roomIds 要删除的房间 id 数组
     * @return 返回删除结果
     */
    boolean deleteBatchRoom(Integer... roomIds);

    /**
     * 业主添加租客
     * @param roomId 房间 id
     * @param userId 租客 id
     * @param price 租金
     * @return 返回结果
     */
    boolean addUserToRoom(Integer roomId, Integer userId, BigDecimal price);
}
