package com.study.myself.xycommon.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: xy-parent
 * @description: 主键类
 * @author: wxy
 * @create: 2020-11-28 20:09
 **/
@Data
public class IdRequest {

    @NotNull(message = "id不可为空")
    private Long id;
}
