package com.study.myself.xycore.advice;



import com.alibaba.fastjson.JSON;
import com.study.myself.xycommon.common.ResultModel;
import com.study.myself.xycommon.enums.ErrorCodeEnum;
import com.study.myself.xycommon.exception.XyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.ServletRequest;
import java.nio.charset.Charset;

/**
 * @program: xy-core
 * @description: 公共的异常封装
 * @author: wxy
 * @create: 2020-12-01 16:12
 **/
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(XyException.class)
    @ResponseBody
    public ResultModel<Boolean> noahExceptionHandler(XyException e, ServletRequest request) {

        try {
            ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper)request;
            logger.error("error in \nurl :{} \ncode:{} \nmsg:{} \nparams:{}\n body:{}",
                ((ContentCachingRequestWrapper)request).getRequestURI(), e.getErrorCode(), e.getMsg(),
                JSON.toJSONString(request.getParameterMap()), StringUtils
                    .toEncodedString(wrapper.getContentAsByteArray(), Charset.forName(wrapper.getCharacterEncoding())));
        } catch (Exception ex) {
            logger.error("==>转换报错{}", ex.getMessage());
        }

        return ResultModel.error(e.getErrorCode(), e.getMsg());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ResultModel<Boolean> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("参数校验错误！", e);

        BindingResult bindingResult = e.getBindingResult();
        String tips = bindingResult.getFieldError().getDefaultMessage();
        return ResultModel.valueOf(ErrorCodeEnum.PARAM_ERROR, tips);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResultModel<Boolean> httpMessageNotReadableExceptionHandler(Exception e) {
        logger.error("参数校验错误！", e);
        return ResultModel.valueOf(ErrorCodeEnum.PARAM_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResultModel<Boolean> noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        logger.error("url错误！", e);
        return ResultModel.valueOf(ErrorCodeEnum.NO_HANDLER_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultModel<Boolean> ExceptionHandler(Exception e) {
        logger.error("程序运行出现异常！", e);
        return ResultModel.valueOf(ErrorCodeEnum.EXCEPTION);

    }
}
