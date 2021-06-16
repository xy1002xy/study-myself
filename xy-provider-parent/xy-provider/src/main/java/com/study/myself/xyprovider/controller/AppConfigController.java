package com.study.myself.xyprovider.controller;

import com.alibaba.fastjson.JSONArray;
import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.utils.DozerUtils;
import com.study.myself.xycommon.utils.YamlUtils;
import com.study.myself.xyprovider.model.DeployAppBO;
import com.study.myself.xyprovider.model.ProductBO;
import com.study.myself.xyprovider.model.request.EditConfigDTO;
import com.study.myself.xyprovider.model.request.IdDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String content = YamlUtils.yamlToJson("xy-provider-parent/xy-provider/src/main/resources/config.yml");
        String json = YamlUtils.convertYamlToJson(content);
        List<DeployAppBO> deployAppBOList = JSONArray.parseArray(json, DeployAppBO.class);
        List<DeployAppBO> resultList = new ArrayList<>();

        // 解析成前端可展示的格式
        for (DeployAppBO deployAppBO : deployAppBOList) {
            List<DeployAppBO.ConfigInfo> subResultList = new ArrayList<>();
            DeployAppBO parent = new DeployAppBO();
            parent.setCurrentSelect(deployAppBO.getCurrentSelect());
            parent.setShow(deployAppBO.getShow());
            parent.setShowName(deployAppBO.getShowName());
            parent.setSkip(deployAppBO.getSkip());
            parent.setOrder(deployAppBO.getOrder());
            List<String> singleList = new ArrayList<>();
            parent.setSingleList(singleList);
            // 遍历有新安装/已有安装的数据
            if (!ObjectUtils.isEmpty(deployAppBO.getCurrentSelect())) {
                singleList.add("新安装");
                singleList.add("选择已有安装");
                // 将下一层的数据拉到第二层
                subResultList = handleSingleList(deployAppBO.getChildren());
                parent.setSingleList(singleList);
                parent.setChildren(subResultList);
                resultList.add(parent);
            } else {
                DeployAppBO deployApp1 = DozerUtils.mapper(deployAppBO, DeployAppBO.class);
                String writeContent = JSONArray.toJSONString(deployApp1);
                String yamlUrl = "xy-provider-parent/xy-provider/src/main/resources/myself.yml";
                Map<String, String> hashMap = new HashMap<>();
                    for (DeployAppBO.ConfigInfo child : deployApp1.getChildren()) {
                        hashMap.put(child.getName(), child.getValue());
                    }
                YamlUtils.createYmlFile(yamlUrl, hashMap);
                resultList.add(deployApp1);
            }
        }
        System.out.println(resultList);
        return ResultModel.succ(resultList);
    }

    /**
     * 解析子节点，将子节点提到第二层
     *
     * @param children 子节点
     * @return 解析子节点，将子节点提到第二层
     */
    private List<DeployAppBO.ConfigInfo> handleSingleList(List<DeployAppBO.ConfigInfo> children) {
        List<DeployAppBO.ConfigInfo> subResultList = new ArrayList<>();
        for (DeployAppBO.ConfigInfo child : children) {
            for (DeployAppBO.ConfigInfo childChild : child.getChildren()) {
                DeployAppBO.ConfigInfo configInfo = new DeployAppBO.ConfigInfo();
                configInfo.setShowName(childChild.getShowName());
                configInfo.setShow(childChild.getShow());
                configInfo.setInput(childChild.getInput());
                configInfo.setDescription(childChild.getDescription());
                configInfo.setName(childChild.getName());
                configInfo.setModifiable(childChild.getModifiable());
                configInfo.setValue(childChild.getValue());
                configInfo.setInstallType(Integer.parseInt(child.getValue()));
                if ("selectServer".equals(childChild.getValue())) {
                    List<String> stringList = new ArrayList<>();
                    stringList.add("服务器1");
                    stringList.add("服务器2");
                    configInfo.setList(stringList);
                    configInfo.setValue("");
                } else {
                    configInfo.setList(new ArrayList<>());
                }
                subResultList.add(configInfo);
            }
        }

        return subResultList;
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
