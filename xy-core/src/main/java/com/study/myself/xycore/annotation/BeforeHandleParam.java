package com.study.myself.xycore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
}


