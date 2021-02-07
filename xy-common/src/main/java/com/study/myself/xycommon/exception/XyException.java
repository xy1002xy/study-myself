package com.study.myself.xycommon.exception;

import com.study.myself.xycommon.enums.ErrorCodeEnum;
import com.study.myself.xycommon.enums.IErrorCode;
import lombok.Getter;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * @program: xy-parent
 * @description: 公共的异常
 * @author: wxy
 * @create: 2020-12-01 16:12
 **/
@Getter
public class XyException extends RuntimeException {

    /**
     * 异常码
     */
    private Integer errorCode = ErrorCodeEnum.EXCEPTION.getCode();
    /**
     * 对用户友好的错误信息
     */
    private String msg;

    public XyException(String message, Integer errorCode) {
        super(message);

        this.errorCode = errorCode;
    }

    public XyException(IErrorCode errorCode, String msg) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        FormattingTuple ft = MessageFormatter.format(errorCode.getMessage(), msg);
        this.msg = ft.getMessage();
    }

    public XyException(String msg) {
        this.msg = msg;
    }

    public XyException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public XyException(Integer errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }
}
