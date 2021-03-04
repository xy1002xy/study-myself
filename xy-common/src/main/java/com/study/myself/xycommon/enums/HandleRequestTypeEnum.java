package com.study.myself.xycommon.enums;

import lombok.Getter;

/**
 * @program: xy-parent
 * @description: 校验请求参数类型
 * @author: wxy
 * @create: 2021-02-22 10:34
 **/
@Getter
public enum HandleRequestTypeEnum {

    NEED_HANDLE_PARAM(1, "拦截入参，需要先校验入参"),
    DECRYPT_HANDLE_PARAM(2, "拦截入参，解密入参"),
    THIRD_HANDLE(3,"第三种处理方式"),
    FOUR_HANDLE(4,"第三种处理方式"),

    ;

    HandleRequestTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;

    private String desc;

}
