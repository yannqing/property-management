package com.qcx.property.enums;

import com.qcx.property.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType implements BaseExceptionInterface {
    // TODO 类型统一摆放
    // common 一般异常
    ARGS_NOT_NULL(50001, "参数不能为空，请重试！"),
    SYSTEM_ERROR(50000, "系统错误"),
    SYSTEM_USER_ERROR(50002, "系统用户无法修改，请重试"),

    // 认证异常
    USERNAME_ALREADY_EXIST(60001, "用户名已存在，请重试！"),
    USERNAME_LENGTH_ERROR(60002, "用户名必须大于 6 个字符，小于等于 15 个字符，请重试！"),
    PASSWORD_LENGTH_ERROR(60003, "密码必须大于等于 8 位，小于 15 位，请重试！"),
    USER_NOT_EXIST(60004, "用户不存在，请重试！"),
    ROLE_ADD_ERROR(60005, "给用户添加角色失败"),
    ROLE_NOT_EXIST(60008, "角色不存在"),
    ROLE_ADMIN_CANNOT_DELETE(60008, "存在角色无法删除"),
    ROLE_ALREADY_EXIST(60010, "角色已经存在，无法添加"),
    PASSWORD_NOT_MATCH(60006, "输入的密码与原来密码不一致，请重试"),
    PASSWORD_NOT_EQUALS(60007, "两次输入的密码不一致，请重试"),
    PERMISSION_NOT_EXIST(60009, "权限不存在"),
    PERMISSION_ALREADY_EXIST(60011, "权限已存在，无法添加"),

    COST_NOT_EXIST(60012, "费用不存在，请重试"),
    COST_TYPE_CODE_REPEAT(60013, "费用类型编码重复！"),

    NO_AUTH_ERROR(60201, "没有权限，请重试"),

    MESSAGE_NOT_EXIST(60014, "消息不存在"),
    EXPRESS_ORDER_NOT_EXIST(60014, "快递订单不存在"),
    EXPRESS_CANNOT_PICK_UP(60015, "快递无法取件"),
    EXPRESS_IS_NOT_BELONG_YOU(60016, "此快递不属于你"),
    EXPRESS_CANNOT_CONFIRM(60017, "无法确认此快递，请联系管理员"),

    TAKEOUTS_NOT_EXIST(60018, "外卖订单不存在，请重试"),
    TAKEOUTS_NOT_DELIVERY(60021, "外卖订单没有在派送中，无法送达"),
    TAKEOUTS_CANNOT_DELIVERY(60030, "订单状态有误，无法接单，请重试"),

    CABINET_NOT_EXIST(60019, "外卖柜不存在，请重试"),
    CABINET_CODE_REPEAT(60020, "外卖柜编号重复！请重试，请重试"),
    CABINET_NOT_AVAILABLE(60022, "外卖柜不可用，请重试，请重试"),

    COMMUNITY_ACTIVITY_NOT_EXIST(60023, "社区活动不存在，请重试"),
    COMMUNITY_ACTIVITY_TITLE_NOT_NULL(60024, "社区活动标题不能为空，请重试"),
    COMMUNITY_ACTIVITY_TYPE_NOT_NULL(60031, "社区活动类型不能为空，请重试"),
    COMMUNITY_ACTIVITY_NOT_IN_REGISTRATION(60028, "活动不在报名时间段，请重试"),
    COMMUNITY_ACTIVITY_NO_ENOUGH_PLACE(60029, "活动没有足够的名额，请重试"),


    ACTIVITY_REGISTRATION_NOT_EXIST(60025, "活动报名不存在，请重试"),
    ACTIVITY_REGISTRATION_ACTIVITY_ID_NOT_NULL(60026, "活动id不能为空，请重试"),
    ACTIVITY_REGISTRATION_USER_ID_NOT_NULL(60027, "参赛用户id不能为空，请重试"),



    //token 异常
    TOKEN_NOT_EXIST(60101, "token 不存在"),
    TOKEN_INVALID(60102, "token 无效")
    ;
    // 异常码
    private final int errorCode;
    // 错误信息
    private final String errorMessage;
}
