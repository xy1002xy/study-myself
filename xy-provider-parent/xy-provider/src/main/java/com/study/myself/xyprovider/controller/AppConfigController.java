package com.study.myself.xyprovider.controller;

import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xyprovider.model.DeployAppBO;
import com.study.myself.xyprovider.model.ProductBO;
import com.study.myself.xyprovider.model.request.EditConfigDTO;
import com.study.myself.xyprovider.model.request.IdDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: xy-parent
 * @description: 合同controller
 * @author: wxy
 * @create: 2021-05-10 13:37
 **/
@RequestMapping("/v1/rest/app/config")
@RestController
@Api(tags = "应用配置相关接口")
@Slf4j
public class AppConfigController {

    @GetMapping("/products")
    @ApiOperation(value = "获取产品列表数据", notes = "获取产品列表数据")
    public ResultModel<List<ProductBO>> products() {
        return null;
    }

    @PostMapping("/appConfigDetail")
    @ApiOperation(value = "获取产品模版数据", notes = "获取产品模版数据")
    public ResultModel<List<DeployAppBO>> appConfigDetail(@RequestBody IdDTO idDTO) {
        return null;
    }

    @PostMapping("/submitAppConfig")
    @ApiOperation(value = "配置信息提交", notes = "配置信息提交")
    public ResultModel<Boolean> submitAppConfig(@RequestBody @Validated EditConfigDTO editConfigDTO) {

        return null;
    }

    @PostMapping("/updateTemplate")
    @ApiOperation(value = "更新配置模版", notes = "更新配置模版")
    public ResultModel<Boolean> updateTemplate(@RequestBody @Validated EditConfigDTO editConfigDTO) {

        return null;
    }
}
