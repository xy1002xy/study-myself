package com.study.myself.xyprovider.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: xy-parent
 * @description: 编辑配置文件
 * @author: wxy
 * @create: 2021-06-09 15:28
 **/
@Data
public class EditConfigDTO {

    @ApiModelProperty(value = "产品id")
    private Long productId;

    @ApiModelProperty(value = "模版配置")
    private List<DeployAppDTO> deployAppDTO;
}
