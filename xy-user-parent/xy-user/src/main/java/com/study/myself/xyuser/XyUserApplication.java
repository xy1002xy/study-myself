package com.study.myself.xyuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author wuxiangyang
 */
@SpringBootApplication(scanBasePackages = "com.study.myself")
public class XyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyUserApplication.class, args);
    }

}
