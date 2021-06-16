package com.study.myself.xyprovider.model;

import com.study.myself.xyprovider.model.request.DeployAppDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @program: zj-seal-platform
 * @description: 接口详情
 * @author: wxy
 * @create: 2020-11-02 16:15
 **/
@Data
@Accessors(chain = true)
public class DeployAppBO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页面展示的名称")
    private String showName;

    @ApiModelProperty(value = "字段定义")
    private String name;

    @ApiModelProperty(value = "计划发布顺序")
    private Integer order;

    @ApiModelProperty(value = "是否跳过 1 跳过 0 不跳过")
    private Integer skip;

    @ApiModelProperty(value = "是否展示 1 展示 0 不展示")
    private Integer show;

    private Integer currentSelect;

    @ApiModelProperty(value = "单选框选择的数据，主要针对新安装和已有安装")
    private List<String> singleList;

    @ApiModelProperty(value = "配置列表")
    private List<ConfigInfo> children;

    /**
     * 入参
     */
    @Data
    public static class ConfigInfo {

        @ApiModelProperty(value = "安装类型 1 新安装 2 选择已有安装")
        private Integer installType;

        @ApiModelProperty(value = "页面展示的名称")
        private String showName;

        @ApiModelProperty(value = "该配置对应的属性含义")
        private String description;

        @ApiModelProperty(value = "字段定义")
        private String name;

        @ApiModelProperty(value = "字段值")
        private String value;

        @ApiModelProperty(value = "前端页面展示的输入框格式 input 输入框 ,radio 单选 , select下拉框 , checkbox 多选 ,textarea 文本输入框")
        private String input;

        @ApiModelProperty(value = "是否展示 1 展示 0 不展示")
        private Integer show;

        @ApiModelProperty(value = "value对应的值是否可修改 1 可修改 2 不可修改 前端需要置灰")
        private Integer modifiable;

        @ApiModelProperty(value = "配置列表")
        private List<ConfigInfo> children;

        @ApiModelProperty(value = "下拉框所选择的数据")
        private List<String> list;
    }

}
