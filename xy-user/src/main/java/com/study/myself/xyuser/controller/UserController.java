package com.study.myself.xyuser.controller;

import com.study.myself.xyuser.model.IdRequest;
import com.study.myself.xyuser.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;

/**
 * @program: xy-parent
 * @description: 服务提供者
 * @author: wxy
 * @create: 2020-11-28 20:08
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
@RefreshScope
public class UserController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/getPort/{id}")
    @ApiOperation(value = "测试获取的端口", notes = "测试获取的端口")
    public String getPort(@PathVariable(name = "id") Long id) {
        StringJoiner stringJoiner = new StringJoiner(": ");
        return stringJoiner.add("该服务的端口").add(port).add("输入的id").add(id.toString()).toString();

    }

    @PostMapping("/getUser")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息")
    public User getStudent(@RequestBody IdRequest idRequest) {
        return null;
    }
}
