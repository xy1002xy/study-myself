package com.study.myself.xyprovider.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: xy-parent
 * @description: 主见类
 * @author: wxy
 * @create: 2020-11-28 20:09
 **/
@Data
public class IdRequest {

    @ApiModelProperty("主键")
    private Long id;
}
