package com.qcx.property.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean复制工具类
 * 对Spring BeanUtils的简单封装
 */
public class BeanCopyUtils {

    /**
     * 单个对象属性复制
     *
     * @param source 源对象
     * @param clazz  目标对象类型
     * @param <T>    目标对象泛型
     * @return 目标对象实例
     */
    public static <T> T copyBean(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        // 创建目标对象
        T result = null;
        try {
            result = clazz.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 复制对象属性到已有对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制对象属性到已有对象，忽略指定的属性
     *
     * @param source             源对象
     * @param target             目标对象
     * @param ignoreProperties   忽略的属性名
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * 复制List集合
     *
     * @param sourceList 源对象列表
     * @param clazz      目标对象类型
     * @param <S>        源对象泛型
     * @param <T>        目标对象泛型
     * @return 目标对象列表
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> clazz) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>(0);
        }
        
        return sourceList.stream()
                .map(source -> copyBean(source, clazz))
                .collect(Collectors.toList());
    }
} 