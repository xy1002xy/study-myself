package com.study.myself.xyuser.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xyuserapi.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;

/**
 * @program: xy-user
 * @description: 服务提供者
 * @author: wxy
 * @create: 2020-11-28 20:08
 **/
@RestController
@RequestMapping("/nacos")
@Api(tags = "Nacos管理")
@RefreshScope
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
public class NacosController {



    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties ;

    @Autowired
    NacosRegistration nacosRegistration;

    @Autowired
    NacosNamingService nacosNamingService ;



    @GetMapping(  "/api/nacos/deregister")
    @ApiOperation(value = "nacos下线", notes = "nacos下线")
    public String deregisterInstance() {
       String serviceName = nacosDiscoveryProperties.getService();
        int port = nacosDiscoveryProperties.getPort();
        String ip = nacosDiscoveryProperties.getIp();
        String groupName = nacosDiscoveryProperties.getGroup();
        String clusterName = nacosDiscoveryProperties.getClusterName();
        log.info("deregister from nacos, serviceName:{}, groupName:{}, clusterName:{}, ip:{}, port:{}", serviceName, groupName, clusterName, ip, port);
        try {
            nacosNamingService.deregisterInstance(serviceName, groupName, ip, port, clusterName);
        } catch (NacosException e) {
            log.error("deregister from nacos error", e);
            return "error";
        }
        return "success";
    }
}
