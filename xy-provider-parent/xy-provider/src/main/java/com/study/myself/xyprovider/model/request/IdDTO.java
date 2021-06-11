package com.study.myself.xyprovider.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: xy-parent
 * @description: 主键类
 * @author: wxy
 * @create: 2020-11-28 20:09
 **/
@Data
public class IdDTO {

    @NotNull(message = "id不可为空")
    @ApiModelProperty(value = "产品id")
    private Long productId;
}
