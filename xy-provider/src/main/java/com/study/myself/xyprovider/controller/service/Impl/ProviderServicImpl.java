package com.study.myself.xyprovider.controller.service.Impl;

import com.study.myself.xyprovider.controller.service.IProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

/**
 * @program: xy-parent
 * @description: 服务service实现类
 * @author: wxy
 * @create: 2020-11-28 20:49
 **/
@Service
@RefreshScope
public class ProviderServicImpl implements IProviderService {

    @Value("${xy.test.name}")
    private String name;


    @Override
    public String getPort(Long id, String port) {
        StringJoiner stringJoiner = new StringJoiner(": ");
        return stringJoiner.add("该服务的端口").add(port).add("输入的id").add(id.toString()).add("配置文件的名称").add(name).toString();
    }
}
