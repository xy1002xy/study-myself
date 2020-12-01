package com.study.myself.xycommon.common;

import com.study.myself.xycommon.enums.ErrorCodeEnum;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @program: xy-parent
 * @description: 封装返回实体
 * @author: wxy
 * @create: 2020-12-01 16:19
 **/
@Data
public class ResultModel<T> implements Serializable {

    /**
     * 返回错误码
     */
    private Integer code;

    /**
     * 返回错误信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResultModel() {
    }

    public ResultModel(Integer code) {
        this.code = code;
    }

    public ResultModel(Integer code, T data, String message) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResultModel(T data) {
        this.data = data;
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.message = ErrorCodeEnum.SUCCESS.getMessage();
    }

    public ResultModel(ErrorCodeEnum errorCode) {
        super();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode) {
        return valueOf(resultCode, null, null, 0);
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode, String message) {
        return valueOf(resultCode, null, message, 0);
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode, T data) {
        return valueOf(resultCode, data, null, 0);
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode, T data, String message, int hasMore) {
        if (StringUtils.isEmpty(message)) {
            message = resultCode.getMessage();
        }
        return new ResultModel<T>(resultCode.getCode(), data, message);
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode, T data, String message) {
        if (StringUtils.isEmpty(message)) {
            message = resultCode.getMessage();
        }
        return new ResultModel<T>(resultCode.getCode(), data, message);
    }

    public static <T> ResultModel<T> valueOf(ErrorCodeEnum resultCode, T data, int count, int size) {
        int hasMore = setHasMore(count, size);
        return valueOf(resultCode, data, null, hasMore);
    }

    public static <T> ResultModel<T> error(Integer errorCode, String msg) {
        return new ResultModel<>(errorCode, msg);
    }

    private static int setHasMore(int count, int size) {
        return count >= size ? 1 : 0;
    }

    public boolean success() {
        return this.code == ErrorCodeEnum.SUCCESS.getCode();
    }

    public static <T> ResultModel<T> succ() {
        return new ResultModel<>(ErrorCodeEnum.SUCCESS);
    }

    public static <T> ResultModel<T> succ(T data) {
        return new ResultModel<>(data);
    }
}
