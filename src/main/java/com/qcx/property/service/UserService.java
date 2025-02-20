package com.qcx.property.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcx.property.domain.dto.user.AddUserDto;
import com.qcx.property.domain.dto.user.QueryUserDto;
import com.qcx.property.domain.dto.user.UpdateMyInfoDto;
import com.qcx.property.domain.dto.user.UpdateUserDto;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.entity.Role;
import com.qcx.property.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcx.property.domain.vo.user.MySelfInfoVo;
import com.qcx.property.domain.vo.user.UserVo;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author 67121
* @description 针对表【user】的数据库操作Service
* @createDate 2025-01-10 15:45:04
*/
public interface UserService extends IService<User> {

    /**
     * 管理员新增用户
     * @param addUserDto 新增 dto
     * @return 返回新增结果
     */
    boolean addUser(AddUserDto addUserDto);

    /**
     * 管理员删除单个用户
     * @param id 删除的用户 id
     * @return 返回删除结果
     */
    boolean deleteUserById(Integer id);

    /**
     * 批量删除用户
     * @param userIds 删除的用户 id 数组
     * @return 返回删除结果
     */
    int deleteBatchUser(Integer... userIds);

    /**
     * 查询单个用户
     * @param id 用户 id
     * @return 查询结果
     */
    UserVo getUserById(Integer id);

    /**
     * 查询所有用户
     * @param queryUserDto 查询 dto
     * @return 返回查询结果
     */
    Page<UserVo> getAll(QueryUserDto queryUserDto);

    /**
     * 管理员修改用户信息
     * @param updateUserDto 更新 dto
     * @return 返回更新结果
     */
    boolean updateUserByAdmin(UpdateUserDto updateUserDto);

    /**
     * 更新个人信息
     * @param updateMyInfoDto 更新 dto
     * @param request 会话 request
     * @return 返回更新结果
     * @throws JsonProcessingException token 解析异常
     */
    boolean updateMyInfo(UpdateMyInfoDto updateMyInfoDto, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 修改个人密码
     * @param originPassword 原始密码
     * @param newPassword 新密码
     * @param againPassword 再次确认密码
     * @param request 会话
     * @return 返回修改结果
     */
    boolean updatePassword(String originPassword, String newPassword, String againPassword, HttpServletRequest request) throws JsonProcessingException;

    /**
     * 管理员重置用户密码
     * @param id 用户 id
     * @return 返回更新结果
     */
    boolean resetUserPassword(Integer id);

    /**
     * 给用户添加角色
     * @param userId 用户 id
     * @param roleIds 角色 id 数组
     * @return 返回添加结果
     */
    boolean addRoleToUser(Integer userId, Integer... roleIds);

    /**
     * 查询用户角色
     * @param userId 要查询的用户id
     * @return 返回查询结果
     */
    List<Role> getRoleByUser(Integer userId);

    /**
     * 查询用户的权限
     * @param userId 用户 id
     * @return 查询结果
     */
    List<Permissions> getPermissionByUser(Integer userId);

    /**
     * 获取个人信息
     * @param request 会话
     * @return 返回结果
     * @throws JsonProcessingException token 解析异常
     */
    MySelfInfoVo getMyselfInfo(HttpServletRequest request) throws JsonProcessingException;

    /**
     * 查看我的租客（仅限业主使用）
     * @param request 会话
     * @return 查询结果
     * @throws JsonProcessingException 异常
     */
    List<UserVo> getMyUsers(HttpServletRequest request) throws JsonProcessingException;

    /**
     * 给业主添加租客
     * @param ownerId 业主 id
     * @param userIds 租客 id 数组
     * @return 返回添加结果
     */
    boolean addUserToOwner(Integer ownerId, Integer... userIds);

    /**
     * 验证用户 id 的有效性
     * @param userId 用户 id
     * @return 返回用户 user
     */
    User verifyUserId(Integer userId);

    /**
     * 验证 token
     * @param request 会话
     * @return 返回用户 user
     * @throws JsonProcessingException 异常
     */
    User verifyToken(HttpServletRequest request) throws JsonProcessingException;
}
