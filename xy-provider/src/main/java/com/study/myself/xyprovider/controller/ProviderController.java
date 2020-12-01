package com.study.myself.xyprovider.controller;

import com.study.myself.xycommon.enums.ErrorCodeEnum;
import com.study.myself.xycommon.exception.XyException;
import com.study.myself.xyprovider.controller.service.IProviderService;
import com.study.myself.xyprovider.model.IdRequest;
import com.study.myself.xyprovider.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class ProviderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private IProviderService providerService;

    @GetMapping("/getPort/{id}")
    @ApiOperation(value = "测试获取的端口", notes = "测试获取的端口")
    public String getPort(@PathVariable(name = "id") Long id) {
        return providerService.getPort(id, port);
    }

    @PostMapping("/getById")
    @ApiOperation(value = "获取学生信息", notes = "获取学生信息")
    public Student getStudent(@RequestBody IdRequest idRequest) {
        throw new XyException(ErrorCodeEnum.FAILURE);
    }
}
