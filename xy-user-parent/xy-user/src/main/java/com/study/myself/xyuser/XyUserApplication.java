package com.study.myself.xyuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author wuxiangyang
 */
@SpringBootApplication(scanBasePackages = "com.study.myself")
public class XyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyUserApplication.class, args);
    }

}
