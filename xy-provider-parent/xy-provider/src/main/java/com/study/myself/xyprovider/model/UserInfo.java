package com.study.myself.xyprovider.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: xy-parent
 * @description: 用户信息
 * @author: wxy
 * @create: 2020-12-04 16:44
 **/
@Data
@Accessors(chain = true)
public class UserInfo {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("性别")
    private Integer sex;
}
