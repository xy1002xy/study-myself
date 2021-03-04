package com.study.myself.xyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wuxiangyang
 */
@SpringBootApplication(scanBasePackages = "com.study.myself")
@EnableFeignClients(basePackages = {"com.study.myself.xyuserapi.feign"})
@EnableDiscoveryClient
@EnableHystrix
public class XyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyProviderApplication.class, args);
    }

}
