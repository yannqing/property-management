package com.qcx.property.aop;

import com.qcx.property.annotation.AuthCheck;
import com.qcx.property.domain.entity.Permissions;
import com.qcx.property.domain.entity.User;
import com.qcx.property.enums.PermissionType;
import com.qcx.property.enums.ErrorType;
import com.qcx.property.exception.BusinessException;
import com.qcx.property.service.PermissionsService;
import com.qcx.property.service.UserService;
import com.qcx.property.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * 权限校验 AOP
 *
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    @Resource
    private PermissionsService permissionsService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 1. 获取到接口所需权限
        String code = authCheck.code();
        // 接口不需要权限，直接放行
        if (code == null) {
            return joinPoint.proceed();
        }
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 2. 查看所需权限是否在数据库中
        Permissions permission = permissionsService.getPermissionByCode(code);
        // 数据库中无所需权限，抛异常：SYSTEM ERROR
        if (permission == null) {
            throw new BusinessException(ErrorType.SYSTEM_ERROR);
        }
        // 3. 获取用户的权限
        // 当前登录用户
        String token = request.getHeader("token");
        User loginUser = JwtUtils.getUserFromToken(token);
        List<Permissions> permissionByLoginUser = userService.getPermissionByUser(loginUser.getUserId());

        // 4. 判断用户是否拥有访问的权限
        if (permissionByLoginUser == null) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }
        List<Permissions> newPermission = permissionByLoginUser.stream().filter(per -> per.getCode().equals(permission.getCode())).toList();
        if (newPermission.isEmpty()) {
            throw new BusinessException(ErrorType.NO_AUTH_ERROR);
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}