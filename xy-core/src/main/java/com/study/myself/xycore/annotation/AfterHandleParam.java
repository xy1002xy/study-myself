package com.study.myself.xycore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: xy-core
 * @description:  返回参数拦截
 * @author: wxy
 * @create: 2021-02-18 16:32
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterHandleParam {
    // String[] values() default {"123","234"};
     String json() default "[{ \"paramName\":\"mobile\", \"handleType\":2},{ \"paramName\":\"name\", \"handleType\":3 }]";
}
