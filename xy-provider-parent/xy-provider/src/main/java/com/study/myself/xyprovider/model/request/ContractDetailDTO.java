package com.study.myself.xyprovider.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: xy-parent
 * @description: 合同详情数据
 * @author: wxy
 * @create: 2021-05-10 13: **/
@Data
public class ContractDetailDTO {
        @ApiModelProperty(value = "合同表主键id")
        private  Long id ;
        @ApiModelProperty(value = "合同生效日期")
        private Date validityStart ;
        @ApiModelProperty(value = "合同到期时间")
        private Date validityEnd ;
        @ApiModelProperty(value = "合同签署完成时间")
        private Date finishTime ;
        @ApiModelProperty(value = "证据链ID-统一证据编码")
        private String ceId ;
        @ApiModelProperty(value = "合同编码")
        private String contractNum ;
        @ApiModelProperty(value = "合同名称")
        private String contractName ;
        @ApiModelProperty(value = "合同文件地址")
        private String fileUrl ;
        @ApiModelProperty(value = "合同类型-（01劳动合同98存量合同）")
        private String type ;
        @ApiModelProperty(value = "劳动者姓名")
        private String workerName ;
        @ApiModelProperty(value = "劳动者地址")
        private String workerAddress ;
        @ApiModelProperty(value = "劳动者证件类型,详细如下：（01身份证  02护照  03港澳台居民居住证  04外国人永久居留身份证）")
        private String cardType ;
        @ApiModelProperty(value = "劳动者证件号码")
        private String cardId ;
        @ApiModelProperty(value = "劳动者手机号")
        private String phone ;
        @ApiModelProperty(value = "用人单位名称")
        private String employerName ;
        @ApiModelProperty(value = "用人单位统一社会信用代码")
        private String employerCode ;

    }

