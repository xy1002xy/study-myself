package com.study.myself.xygateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @program: xy-parent
 * @description: 全局过滤器
 * @author: wxy
 * @create: 2020-11-28 23:54
 **/
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("全局过滤器校验开始.......");
        ServerHttpRequest request = exchange.getRequest();
        // String userName = request.getQueryParams().getFirst("username");
        // String userName = request.getHeaders().getFirst("username");
        // if (StringUtils.isEmpty(userName)){
        //     log.error("非法用户闯入");
        //     exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
        //     return exchange.getResponse().setComplete();
        // }
        log.info("全局过滤器校验结束.......");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
