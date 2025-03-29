package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.annotation.AuthCheck;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.Room;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.domain.vo.room.RoomVo;
import com.qcx.property.service.RoomService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @description: 房间管理
 * @author: yannqing
 * @create: 2025-02-09 10:03
 * @from: <更多资料：yannqing.com>
 **/
@Tag(name = "房间管理")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    private RoomService roomService;

    @AuthCheck(code = "ROOM_GET_ALL_BY_ADMIN")
    @Operation(summary = "查询所有房间(管理员）")
    @GetMapping("/admin")
    public BaseResponse<?> getAllRooms(QueryRoomDto queryRoomDto) {
        Page<RoomVo> costList = roomService.getAllRooms(queryRoomDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部房间成功！");
    }

    @AuthCheck(code = "ROOM_GET_ALL_BY_OWNER")
    @Operation(summary = "查询所有房间（业主）")
    @GetMapping("/owner")
    public BaseResponse<?> getAllRoomsByOwner(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException {
        Page<RoomVo> costList = roomService.getAllRoomsByOwner(queryRoomDto, request);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部房间成功！");
    }

    @AuthCheck(code = "ROOM_GET_ALL_BY_USER")
    @Operation(summary = "查询所有房间（租客）")
    @GetMapping("/user")
    public BaseResponse<?> getAllRoomsByUser(QueryRoomDto queryRoomDto, HttpServletRequest request) throws JsonProcessingException {
        Page<RoomVo> costList = roomService.getAllRoomsByUser(queryRoomDto, request);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部房间成功！");
    }

    @AuthCheck(code = "ROOM_UPDATE")
    @PutMapping
    @Operation(summary = "更新房间")
    public BaseResponse<?> updateRoom(UpdateRoomDto updateRoomDto) {
        boolean result = roomService.updateRoom(updateRoomDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "修改房间成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "修改房间失败！");
        }
    }

    @AuthCheck(code = "ROOM_ADD")
    @Operation(summary = "添加新房间")
    @PostMapping
    public BaseResponse<?> addRoom(AddRoomDto addRoomDto) {
        boolean result = roomService.addRoom(addRoomDto);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "新增房间成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "新增房间失败！");
        }
    }

    @AuthCheck(code = "ROOM_DELETE_ONE")
    @DeleteMapping("/{id}")
    @Parameters(@Parameter(name = "id", description = "房间 id", required = true, in = ParameterIn.PATH))
    @Operation(summary = "删除单个房间")
    public BaseResponse<?> deleteRoom(@PathVariable Integer id) {
        boolean result = roomService.deleteRoom(id);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "删除房间成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "删除房间失败！");
        }
    }

    @AuthCheck(code = "ROOM_DELETE_BATCH")
    @DeleteMapping("/batch")
    @Parameters(@Parameter(name = "roomIds", description = "房间 id 数组", required = true))
    @Operation(summary = "批量删除房间")
    public BaseResponse<?> deleteBatchRoom(Integer... roomIds) {
        boolean result = roomService.deleteBatchRoom(roomIds);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除房间成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除房间失败！");
        }
    }

    @AuthCheck(code = "ROOM_ADD_USER")
    @DeleteMapping("/add-user")
    @Parameters({
            @Parameter(name = "roomId", description = "房间 id", required = true),
            @Parameter(name = "userId", description = "租客 id", required = true),
            @Parameter(name = "price", description = "租金", required = true),
    })
    @Operation(summary = "业主添加租客")
    public BaseResponse<?> addUserToRoom(Integer roomId, Integer userId, BigDecimal price) {
        boolean result = roomService.addUserToRoom(roomId, userId, price);
        if (result) {
            return ResultUtils.success(Code.SUCCESS, null, "批量删除房间成功！");
        } else {
            return ResultUtils.failure(Code.FAILURE, null, "批量删除房间失败！");
        }
    }


}
