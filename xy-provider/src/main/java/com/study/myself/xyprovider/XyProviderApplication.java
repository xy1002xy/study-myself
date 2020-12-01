package com.study.myself.xyprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class XyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XyProviderApplication.class, args);
    }

}
