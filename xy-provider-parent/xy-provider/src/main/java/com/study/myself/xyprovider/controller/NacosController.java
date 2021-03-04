package com.study.myself.xyprovider.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.study.myself.xyprovider.model.OfflineNacos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: xy-parent
 * @description: nacos暴露的接口
 * @author: wxy
 * @create: 2021-03-04 11:52
 **/
@RestController
@RequestMapping("/nacos")
@Api(tags = "NACOS管理")
@RefreshScope
@Slf4j
public class NacosController {

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;



    @Value("${spring.application.name}")
    private String service;

    @Autowired
    private ServiceRegistry serviceRegistry;
    @Autowired
    private NacosNamingService nacosNamingService;

    @PostMapping("/api/nacos/offline")
    @ApiOperation(value = "nacos下线", notes = "nacos下线")
    public String offline(@RequestBody OfflineNacos offlineNacos) throws NacosException {
        String serviceName = offlineNacos.getServiceName();
        try {
            Thread.sleep(150);
            // 获取服务名为 xy-provider 的实例信息
            List<Instance> serverProvider = nacosNamingService.getAllInstances(serviceName);
            log.info("deregisterInstance--->开始{}", serverProvider);
            nacosNamingService
                .deregisterInstance(offlineNacos.getServiceName(), nacosDiscoveryProperties.getGroup(), offlineNacos.getIp(), offlineNacos.getPort(),
                    nacosDiscoveryProperties.getClusterName());
            serviceRegistry.close();
            log.info("deregisterInstance--->下线结束{}", serverProvider);
        } catch (NacosException  e) {
            log.error("deregister from nacos error", e);
            return "error";
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

    @PostMapping("/api/nacos/deregister")
    @ApiOperation(value = "deregister", notes = "deregister")
    public String deregisterInstance() throws NacosException {
        String ip = nacosDiscoveryProperties.getIp();
        try {
            // 同一个服务注册两个实例
            log.info("service---<{},,ip---<{}", service, ip);
            Instance instance = new Instance();
            instance.setIp(ip);
            instance.setPort(8088);
            instance.setWeight(1.0D);
            instance.setClusterName(nacosDiscoveryProperties.getClusterName());
            Map<String, String> metadata = new HashMap<>();
            metadata.put("preserved.register.source", "SPRING_CLOUD");
            instance.setMetadata(metadata);
            nacosNamingService.registerInstance(service,instance);
            nacosNamingService.registerInstance(service, ip, 8089);
            // 获取服务名为 xy-provider 的实例信息
            List<Instance> serverProvider = nacosNamingService.getAllInstances(service);
            log.info("deregisterInstance--->开始{}", serverProvider);
            //  namingService.deregisterInstance(service, nacosDiscoveryProperties.getGroup(), ip, 8089, nacosDiscoveryProperties.getClusterName());
            // nacosNamingService
            //     .deregisterInstance(service, nacosDiscoveryProperties.getGroup(), nacosDiscoveryProperties.getIp(), 8081,
            //         nacosDiscoveryProperties.getClusterName());
            // Thread.sleep(1000);
            log.info("deregisterInstance--->下线结束{}", serverProvider);
        } catch (NacosException e) {
            log.error("deregister from nacos error", e);
            return "error";
        }
        return "success";
    }
}
