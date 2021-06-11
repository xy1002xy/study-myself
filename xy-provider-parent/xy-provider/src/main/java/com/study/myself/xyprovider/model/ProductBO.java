package com.study.myself.xyprovider.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: xy-parent
 * @description: 产品返回参数
 * @author: wxy
 * @create: 2021-06-09 13:46
 **/
@Data
public class ProductBO {

    @ApiModelProperty(value = "产品Id")
    private Long productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

}
