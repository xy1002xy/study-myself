package com.study.myself.xygateway.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @program: xy-parent
 * @description: 自定义拦截器
 * @author: wxy
 * @create: 2020-12-17 20:35
 **/
@Component
@Slf4j
public class CustomFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String pattern = "/xy-gateway/provider/**";
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (!antPathMatcher.match(pattern, path)) {
            return chain.filter(exchange);
        }
        String appId = request.getHeaders().getFirst("appId");
        String signature = request.getHeaders().getFirst("signature");
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(signature)) {
            throw new IllegalArgumentException("");
        }
        String method = request.getMethodValue();

        if ("POST".equals(method)) {
            return handlePostRequest(exchange, signature, "", chain);
        } else if ("GET".equals(method)) {
            Map requestQueryParams = request.getQueryParams();
            //TODO 得到Get请求的请求参数后，做你想做的事

            return chain.filter(exchange);
        }

        return chain.filter(exchange);
    }

    /**
     * 拦截请求体并做校验
     *
     * @param exchange
     * @param signature
     * @param appSecret
     * @param chain
     * @return 拦截请求体并做校验
     */
    private Mono<Void> handlePostRequest(ServerWebExchange exchange, String signature, String appSecret,
        GatewayFilterChain chain) {
        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer ->
        {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            // 得到Post请求的请求参数
            // boolean flag = checkSignature(appSecret, bodyString, signature);
            // if (!flag) {
            //     throw new IllegalArgumentException("校验失败");
            //     //return Mono.error(new IllegalArgumentException("所传签名有问题，请检查"));
            // }
            log.info("得到Post请求的请求参数====>{}", bodyString);

            exchange.getAttributes().put("POST_BODY", bodyString);
            DataBufferUtils.release(dataBuffer);

            Flux<DataBuffer> cachedFlux = Flux.defer(() ->
            {
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                return Mono.just(buffer);
            });
            //下面的将请求体再次封装写回到request里，传到下一级，否则，由于请求体已被消费，后续的服务将取不到值
            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }
            };
            //封装request，传给下一级
            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        });
    }

}
