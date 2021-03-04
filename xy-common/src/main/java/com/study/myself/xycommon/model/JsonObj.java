package com.study.myself.xycommon.model;

import lombok.Data;

/**
 * @program: xy-parent
 * @description: 参数解析实体
 * @author: wxy
 * @create: 2021-03-04 18:05
 **/
@Data
public class JsonObj {
    /**
     * 请求参数名称
     */
    private String paramName;
    /**
     * 请求方式
     */
    private Integer handleType;
}
