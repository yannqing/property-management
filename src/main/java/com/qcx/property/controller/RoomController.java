package com.qcx.property.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcx.property.common.Code;
import com.qcx.property.domain.dto.room.AddRoomDto;
import com.qcx.property.domain.dto.room.QueryRoomDto;
import com.qcx.property.domain.dto.room.UpdateRoomDto;
import com.qcx.property.domain.entity.Room;
import com.qcx.property.domain.model.BaseResponse;
import com.qcx.property.service.RoomService;
import com.qcx.property.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "查询所有房间")
    @GetMapping
    public BaseResponse<?> getAllRooms(QueryRoomDto queryRoomDto) {
        Page<Room> costList = roomService.getAllRooms(queryRoomDto);
        return ResultUtils.success(Code.SUCCESS, costList, "查询全部房间成功！");
    }

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


}
