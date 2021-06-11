package com.study.myself.xyuser.controller;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.study.myself.xycommon.enums.HandleRequestTypeEnum;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xycommon.utils.DozerUtils;
import com.study.myself.xycore.annotation.AfterHandleParam;
import com.study.myself.xycore.annotation.BeforeHandleParam;
import com.study.myself.xyuserapi.outvo.UserOutVo;
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

import java.util.ArrayList;
import java.util.List;
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
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
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
    @ApiOperation(value = "获取用户信息-feign", notes = "获取用户信息-feign")
    public UserVo getUser(@RequestBody IdRequest idRequest) {
        UserVo user = new UserVo();
        user.setId(idRequest.getId()).setName("用户1").setSex(1).setMobile("18221232312");
        return user;
    }

    @PostMapping("/getUserEx")
    @ApiOperation(value = "获取用户信息-ex", notes = "获取用户信息")
    public UserOutVo getUserEx(@RequestBody IdRequest idRequest) {
        UserOutVo user = new UserOutVo();
        user.setId(idRequest.getId()).setName("用户1").setSex(1);
        return user;
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "获取用户信息-列表", notes = "获取用户信息-列表")
    public List<UserOutVo> listUser(@RequestBody IdRequest idRequest) {
        List<UserOutVo> userOutVoList = new ArrayList<>();
        UserOutVo user = new UserOutVo();
        user.setId(idRequest.getId()).setName("用户1").setSex(1);
        userOutVoList.add(user);
        return userOutVoList;
    }

    @PostMapping("/handle")
    @ApiOperation(value = "测试-动态修改用户出入参", notes = "测试-动态修改用户出入参")
    @AfterHandleParam(json = "[{ \"paramName\":\"name\", \"handleType\":5 }]")
    @BeforeHandleParam(values = {"mobile,2", "name,3"})
    public UserOutVo handleUser(@RequestBody UserVo userVo) {
        return DozerUtils.mapper(userVo, UserOutVo.class);
    }

    @PostMapping("/handleUserList")
    @ApiOperation(value = "测试-动态修改用户列表出入参", notes = "测试-动态修改用户列表出入参")
    @AfterHandleParam(json = "[{ \"paramName\":\"mobile\", \"handleType\":5 }]")

   // @BeforeHandleParam(values = {"mobile,2", "name,3"})
    public List<UserOutVo> handleUserList(@RequestBody UserVo userVo) {
        List<UserOutVo> userOutVoList = new ArrayList<>();
        UserOutVo userOutVo = new UserOutVo();
        userOutVo.setId(userVo.getId() + 1).setName(userVo.getName() + "名字1").setMobile(userVo.getMobile() + "手机号1");
        userOutVoList.add(userOutVo);
        UserOutVo userOutVo1 = new UserOutVo();
        userOutVo1.setId(userVo.getId() + 2).setName(userVo.getName() + "名字2").setMobile(userVo.getMobile() + "手机号2");
        userOutVoList.add(userOutVo1);
        return userOutVoList;
    }
}
