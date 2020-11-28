package com.study.myself.xygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
public class XyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyGatewayApplication.class, args);
    }

}
