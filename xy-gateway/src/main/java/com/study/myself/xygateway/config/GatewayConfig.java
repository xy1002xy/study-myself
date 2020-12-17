package com.study.myself.xygateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @program: xy-parent
 * @description: 网关配置
 * @author: wxy
 * @create: 2020-12-17 20:58
 **/
@Configuration
public class GatewayConfig {
    /**
     * webflux 静态资源配置
     *
     * @return serverResponse
     */
    @Bean
    RouterFunction<ServerResponse> staticResourceRouter() {
        return RouterFunctions.resources("/webjars/**", new ClassPathResource("webjars/"));
    }
}
