package com.qcx.property.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统角色枚举（对应数据库 role 表格，和 role 表数据一定要同步，尤其是 roleId）
 */
@Getter
@AllArgsConstructor
public enum RoleType {
    ADMIN(0, "admin", "管理员"),
    USER(1, "user", "租客"),
    OTHER(2, "other", "业主")
    ;

    private final int roleId;
    private final String roleCode;
    private final String remark;
}
