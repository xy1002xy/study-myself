package com.study.myself.xyprovider.innercontroller;

import com.study.myself.xycommon.model.IdRequest;
import com.study.myself.xyprovider.model.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: xy-parent
 * @description: 内部服务商controller
 * @author: wxy
 * @create: 2020-12-16 11:50
 **/
@RestController
@RequestMapping("/inner/provider")
@Api(tags = "学生管理")
@RefreshScope
@Slf4j
public class InnerProviderController {

    @PostMapping("/providerInfo")
    @ApiOperation(value = "获取学生信息", notes = "获取学生信息")
    public Student providerInfo(@RequestBody IdRequest idRequest) {
        Student student = new Student();
        student.setId(idRequest.getId()).setName("小明").setSex(1);
        return student;
    }
}
