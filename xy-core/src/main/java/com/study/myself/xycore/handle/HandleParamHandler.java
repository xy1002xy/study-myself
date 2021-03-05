package com.study.myself.xycore.handle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.myself.xycommon.enums.HandleRequestTypeEnum;
import com.study.myself.xycommon.model.JsonObj;
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
import java.util.List;
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
        // String[] handles = checkName.values();
        // HandleRequestTypeEnum requestTypeEnum = checkName.requestType();
        // 转换实体
        List<JsonObj> jsonObjList = JSONObject.parseArray(checkName.json(), JsonObj.class);
        // 将实体转换json对象
        JSONObject json = (JSONObject)JSON.toJSON(obj);
        for (JsonObj jsonObj : jsonObjList) {
            String paramName = jsonObj.getParamName();
            PropertyDescriptor paramDescriptor = BeanUtils.getPropertyDescriptor(clazz, paramName);
            String value = (String)Objects.requireNonNull(paramDescriptor).getReadMethod().invoke(obj);
            // 检查参数解析方式 HandleRequestTypeEnum
            value = commonCheckHandleParam(value, jsonObj.getHandleType());
            // 修改json对象
            json.put(paramName, value);
            log.info("jsonObj----{}", jsonObj);
        }
        // 把修改后的json对象转换为接收到参数的对象保存到数组中
        args[0] = JSON.toJavaObject(json, clazz);
        // 把数组封装到joinPoint对象返回
        return point.proceed(args);
    }

    /**
     * @param value      获取的值
     * @param handleType 处理方式
     * @return 根据不同的处理方式处理值
     */

    private String commonCheckHandleParam(String value, Integer handleType) {
        if (HandleRequestTypeEnum.NEED_HANDLE_PARAM.getValue().equals(handleType)) {
            value = value + "-----第一种请求方式-请求入参数--拦截入参，需要先校验入参:" + UUID.randomUUID().toString().substring(0, 10);
        }
        if (HandleRequestTypeEnum.DECRYPT_HANDLE_PARAM.getValue().equals(handleType)) {
            value = value + "-----第二种请求方式-请求入参数--拦截入参，解密入参:" + UUID.randomUUID().toString().substring(0, 10);
        }
        if (HandleRequestTypeEnum.THIRD_HANDLE.getValue().equals(handleType)) {
            value = value + "-----第三种请求方式-请求入参数--拦截入参:" + UUID.randomUUID().toString().substring(0, 10);
        }
        if (HandleRequestTypeEnum.FOUR_HANDLE.getValue().equals(handleType)) {
            value = value + "-----第四种请求方式-请求入参数--拦截入参:" + UUID.randomUUID().toString().substring(0, 10);
        }
        if (HandleRequestTypeEnum.FIVE_HANDLE.getValue().equals(handleType)) {
            value = value + "-----第五种请求方式-处理返回参数:" + UUID.randomUUID().toString().substring(0, 10);
        }
        return value;
    }

    @AfterReturning(value = "extendAfter()", returning = "obj")
    public void doAfterAdvice(JoinPoint point, Object obj) throws Throwable {
        System.out.println("后置通知执行了!!!!");
        //获取参数信息
        MethodSignature signature = (MethodSignature)point.getSignature();
        AfterHandleParam afterHandleName = signature.getMethod().getAnnotation(AfterHandleParam.class);
        // 转换保存参数对象为数据库对象
        Class<?> clazz = obj.getClass();
        // 转换实体
        List<JsonObj> jsonObjList = JSONObject.parseArray(afterHandleName.json(), JsonObj.class);
        // 将实体转换json对象
        for (JsonObj jsonObj : jsonObjList) {
            PropertyDescriptor paramDescriptor = BeanUtils.getPropertyDescriptor(clazz, jsonObj.getParamName());
            String value = (String)Objects.requireNonNull(paramDescriptor).getReadMethod().invoke(obj);
            value = commonCheckHandleParam(value, jsonObj.getHandleType());
            //获得写方法
            paramDescriptor.getWriteMethod().invoke(obj, value);
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


