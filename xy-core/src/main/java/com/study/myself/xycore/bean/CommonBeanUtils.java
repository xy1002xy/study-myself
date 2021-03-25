package com.study.myself.xycore.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @program: xy-parent
 * @description: bean的工具类 针对注解不生效的时候可以手动加到bean里面
 * @author: wxy
 * @create: 2021-03-17 17:23
 **/
@Component
public class CommonBeanUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        CommonBeanUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> bean) {
        return applicationContext.getBean(bean);
    }

}

