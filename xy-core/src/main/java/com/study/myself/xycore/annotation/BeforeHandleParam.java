package com.study.myself.xycore.annotation;

import com.study.myself.xycommon.enums.HandleRequestTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * @program: xy-core
 * @description:  请求参数拦截
 * @author: wxy
 * @create: 2021-02-18 16:32
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeHandleParam {

    String[] values() default {"123","234"};

    HandleRequestTypeEnum requestType();

}


