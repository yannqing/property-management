package com.qcx.property.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcx.property.domain.entity.UserUser;
import com.qcx.property.service.UserUserService;
import com.qcx.property.mapper.UserUserMapper;
import org.springframework.stereotype.Service;

/**
* @author yanqing
* @description 针对表【user_user(用户关系表)】的数据库操作Service实现
* @createDate 2025-02-20 09:49:04
*/
@Service
public class UserUserServiceImpl extends ServiceImpl<UserUserMapper, UserUser>
    implements UserUserService{

}




