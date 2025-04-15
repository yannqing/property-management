package com.qcx.property.common;

/**
 * 消息状态
 */
public interface MessageStatus {
    // 未读
    int NOT_READ = 0;
    // 已读
    int READ = 1;
    // 待审批
    int WITTING_APPROVAL = 2;
    // 已通过
    int PASSED = 3;
    // 已拒绝
    int REFUSED = 4;
}
