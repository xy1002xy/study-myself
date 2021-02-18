package com.study.myself.xyprovider.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.naming.NacosNamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: xy-parent
 * @description: bean
 * @author: wxy
 * @create: 2021-02-08 10:02
 **/

@Configuration
public class BeanConfig {

    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;

    @Bean
    public NacosNamingService createNacosNamingService() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", nacosDiscoveryProperties.getServerAddr());
        properties.setProperty("namespace", "public");
        return new NacosNamingService(properties);
    }
}
