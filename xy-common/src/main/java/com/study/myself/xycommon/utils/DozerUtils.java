package com.study.myself.xycommon.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: xy-parent
 * @description: 对象转换
 * @author: wxy
 * @create: 2020-12-04 16:50
 **/
public class DozerUtils {
    private static Mapper MAPPER = new DozerBeanMapper();

    /**
     * 映射单个对象（泛型方法）
     * @param s 源对象
     * @param t 目标类型
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> T mapper(S s, Class<T> t) {
        if (s == null) {
            return null;
        }
        try {
            return MAPPER.map(s, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 映射单个对象至给定对象
     * @param s 源对象
     * @param d 目标对象
     */
    public static void mapper(Object s, Object d) {
        MAPPER.map(s, d);
    }

    /**
     * 映射对象列表
     * @param sourceList
     * @param t
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> mapList(List<S> sourceList, Class<T> t) {
        if (sourceList == null) {
            return null;
        }

        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = MAPPER.map(sourceObject, t);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 映射单个对象至map对象
     * @param obj 源对象
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }
}
