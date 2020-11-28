package com.study.myself.xyuser.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: study-demo
 * @description: 用户实体
 * @author: wxy
 * @create: 2020-11-20 17:21
 **/
@Data
@Accessors(chain = true)
public class User {

    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("性别")
    private Integer sex;
}
