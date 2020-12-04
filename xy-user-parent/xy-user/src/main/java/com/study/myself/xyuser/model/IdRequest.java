package com.study.myself.xyuser.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: xy-parent
 * @description: 主键类
 * @author: wxy
 * @create: 2020-11-28 20:09
 **/
@Data
public class IdRequest {

    @ApiModelProperty("主键")
    private Long id;
}
