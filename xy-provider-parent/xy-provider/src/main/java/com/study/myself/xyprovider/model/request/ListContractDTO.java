package com.study.myself.xyprovider.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @program: xy-parent
 * @description: 合同分页查询请求参数
 * @author: wxy
 * @create: 2021-05-10 14:00
 **/
@Data
public class ListContractDTO {
    @ApiModelProperty(value = "页码", required = true)
    @Min(value = 1, message = "pageIndex不能小于1,默认1")
    @NotNull(message = "分页不能为空")
    private Integer pageIndex;
    @ApiModelProperty(value = "页面数据条数", required = true)
    @Min(value = 1, message = "size不能小于1,默认10")
    @Max(value = 20, message = "当页大小必须小于等于20")
    @NotNull(message = "分页不能为空")
    private Integer pageSize;
    @ApiModelProperty(value = "企业名称")
    private String employerName;
    @ApiModelProperty(value = "用户在e签宝的唯一id")
    private String gid;
    @ApiModelProperty(value = "统一社会信用代码号")
    private String employerCode;
    @ApiModelProperty(value = "流程id")
    private String flowId;
    @ApiModelProperty(value = "备案状态;0待推送1推送成功2推送失败")
    private Integer status;
}
