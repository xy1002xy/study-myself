package com.study.myself.xyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wuxiangyang
 */
@SpringBootApplication(scanBasePackages = "com.study.myself")
public class XyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyProviderApplication.class, args);
    }

}
