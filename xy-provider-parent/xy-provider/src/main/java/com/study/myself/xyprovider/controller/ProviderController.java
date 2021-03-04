package com.study.myself.xyprovider.controller;

import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xycommon.utils.DozerUtils;
import com.study.myself.xyprovider.model.Student;
import com.study.myself.xyprovider.model.UserInfo;
import com.study.myself.xyprovider.service.IProviderService;
import com.study.myself.xyuserapi.feign.IUserApiService;
import com.study.myself.xyuserapi.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProviderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private IProviderService providerService;

    @Autowired
    private IUserApiService userApiService;



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


}
