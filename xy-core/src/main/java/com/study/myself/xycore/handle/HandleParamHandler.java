package com.study.myself.xycore.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.myself.xycore.annotation.AfterHandleParam;
import com.study.myself.xycore.annotation.BeforeHandleParam;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.UUID;

/**
 * @program: study-demo
 * @description: 全局拦截器
 * @author: wxy
 * @create: 2020-11-27 16:04
 **/
@Aspect
@Component
@Slf4j
public class HandleParamHandler {

    @Pointcut("@annotation(com.study.myself.xycore.annotation.BeforeHandleParam)")
    public void checkName() {
    }

    @Pointcut("@annotation(com.study.myself.xycore.annotation.AfterHandleParam)")
    public void extendAfter() {
    }

    @Around("checkName()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        // 获取请求参数数组
        Object[] args = point.getArgs();
        Object obj = args[0];
        //获取参数信息
        MethodSignature signature = (MethodSignature)point.getSignature();
        BeforeHandleParam checkName = signature.getMethod().getAnnotation(BeforeHandleParam.class);
        // 转换保存参数对象为数据库对象
        Class<?> clazz = obj.getClass();
        String[] handles = checkName.values();
        // 将实体转换json对象
        JSONObject json = (JSONObject)JSON.toJSON(obj);
        for (String str : handles) {
            PropertyDescriptor paramDescriptor = BeanUtils.getPropertyDescriptor(clazz, str);
            String value = (String)Objects.requireNonNull(paramDescriptor).getReadMethod().invoke(obj);
            value = value + "-----请求入参数--" + UUID.randomUUID().toString().substring(0, 10);
            // 修改json对象
            json.put(str, value);
        }
        // 把修改后的json对象转换为接收到参数的对象保存到数组中
        args[0] = JSON.toJavaObject(json, clazz);
        // 把数组封装到joinPoint对象返回
        return point.proceed(args);
    }

    @AfterReturning(value = "extendAfter()", returning = "obj")
    public void doAfterAdvice(JoinPoint point, Object obj) throws Throwable {
        System.out.println("后置通知执行了!!!!");
        //获取参数信息
        MethodSignature signature = (MethodSignature)point.getSignature();
        AfterHandleParam afterHandleName = signature.getMethod().getAnnotation(AfterHandleParam.class);
        // 转换保存参数对象为数据库对象
        Class<?> clazz = obj.getClass();
        String[] handles = afterHandleName.values();
        for (String str : handles) {
            PropertyDescriptor paramDescriptor = BeanUtils.getPropertyDescriptor(clazz, str);
            String value = (String)Objects.requireNonNull(paramDescriptor).getReadMethod().invoke(obj);
            value = value + "-----返回入参----" + UUID.randomUUID().toString().substring(0, 10);
            PropertyDescriptor pd = new PropertyDescriptor(str, clazz);
            //获得写方法
            Method wM = pd.getWriteMethod();
            wM.invoke(obj, value);
        }

        // Field[] fields = clazz.getDeclaredFields();
        // //写数据
        // for (Field f : fields) {
        //     String value = (String)Objects.requireNonNull(paramDescriptor).getReadMethod().invoke(obj);
        //     value = value + "-----返回入参----" + UUID.randomUUID().toString().substring(0, 10);
        //     if (str.equals(f.getName())) {
        //         PropertyDescriptor pd = new PropertyDescriptor(str, clazz);
        //         //获得写方法
        //         Method wM = pd.getWriteMethod();
        //         wM.invoke(obj, value);
        //     }
        // }
    }


}


