package com.study.myself.xycommon.enums;

import lombok.Getter;

/**
 * @program: xy-parent
 * @description: 错误码
 * @author: wxy
 * @create: 2020-12-01 16:14
 **/
@Getter
public enum ErrorCodeEnum implements IErrorCode {

    /**
     * 系统参数
     */
    SUCCESS(0, "请求成功"),
    FAILURE(1, "请求失败"),
    PARAM_ERROR(400, "参数错误"),
    NO_HANDLER_FOUND(404, "url错误，请检查"),
    EXCEPTION(500, "服务内部出现异常"),
    ;

    private Integer code;

    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
