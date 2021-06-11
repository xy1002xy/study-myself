package com.study.myself.xyprovider.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: xy-parent
 * @description: 合同返回数据
 * @author: wxy
 * @create: 2021-05-10 13:40
 **/
@Data
public class ContractDataBO {
        @ApiModelProperty(value = "合同表主键id")
        private  Long id ;
        @ApiModelProperty(value = "用人单位名称")
        private String employerName ;
        @ApiModelProperty(value = "用户在e签宝的唯一id")
        private String gid ;
        @ApiModelProperty(value = "流程id")
        private String flowId ;
        @ApiModelProperty(value = "流程名称")
        private String flowName ;
        @ApiModelProperty(value = "合同名称-文件名称")
        private String contractName ;
        @ApiModelProperty(value = "创建时间-提交时间")
        private Date createTime ;
        @ApiModelProperty(value = "备案状态;0待推送1已推送2推送失败")
        private Integer status ;

}

