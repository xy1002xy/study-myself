package com.study.myself.xyprovider.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.alibaba.nacos.client.naming.net.NamingProxy;
import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xycommon.utils.DozerUtils;
import com.study.myself.xyprovider.controller.service.IProviderService;
import com.study.myself.xyprovider.model.OfflineNacos;
import com.study.myself.xyprovider.model.Student;
import com.study.myself.xyprovider.model.UserInfo;
import com.study.myself.xyuserapi.feign.IUserApiService;
import com.study.myself.xyuserapi.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: xy-parent
 * @description: 服务提供者
 * @author: wxy
 * @create: 2020-11-28 20:08
 **/
@RestController
@RequestMapping("/provider")
@Api(tags = "学生管理")
@RefreshScope
@Slf4j
public class ProviderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private IUserApiService userApiService;

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    NamingProxy namingProxy ;
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serviceAddr;

    // @Value("${spring.cloud.nacos.discovery.service:${spring.application.name:}}")
    @Value("${spring.application.name}")
    private String service;

    @Autowired
    private ServiceRegistry serviceRegistry;
    // @Autowired
    // private NacosRegistration nacosRegistration;
    //
    @Autowired
    private NacosNamingService nacosNamingService;

    @GetMapping("/getPort/{id}")
    @ApiOperation(value = "测试获取的端口", notes = "测试获取的端口")
    public String getPort(@PathVariable(name = "id") Long id) {
        return providerService.getPort(id, port);
    }

    @PostMapping("/getById")
    @ApiOperation(value = "获取学生信息", notes = "获取学生信息")
    public Student getStudent(@RequestBody IdRequest idRequest) {
        log.info("idRequest------->{}",idRequest);
        Student student = new Student();
        student.setId(10L).setName("小明").setSex(1);
        return student;
    }

    @PostMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public UserInfo getUserInfo(@RequestBody IdRequest idRequest) {
        ResultModel<UserVo> userVoResultModel = userApiService.getUser(idRequest);
        log.info("获取的用户信息{}", userVoResultModel);
        return DozerUtils.mapper(userVoResultModel.getData(), UserInfo.class);
    }

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
