package com.study.myself.xycore.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.myself.xycommon.common.ResultModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;

/**
 * @program: xy-parent
 * @description: 返回参数封装
 * @author: wxy
 * @create: 2020-12-01 16:53
 **/
@ControllerAdvice
@Component
public class XyResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Object EMPTY_OBJ = new Object();
    private static final List EMPTY_LIST = Collections.emptyList();

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
        Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
        ServerHttpResponse serverHttpResponse) {
        String uri = ((ServletServerHttpRequest)serverHttpRequest).getServletRequest().getRequestURI();
        if (uri.matches("(.*)/swagger-resources") || uri.matches("(.*)/v2/api-docs")) {
            return body;
        }

        if (body == null) {
            return ResultModel.succ();
        }

        if (body instanceof ResultModel) {
            return body;
        } else {
            //对于返回的对象如果不是最终对象ResponseResult，则选包装一下
            ResultModel<Object> resultModel = ResultModel.succ(body);
            //因为handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
            if (body instanceof String) {
                return JSON.toJSONString(resultModel);
            }
            return resultModel;
        }
    }

    public static void main(String[] args) {
        String name = "矩阵号";
        System.out.println(ResultModel.succ(name));
        System.out.println(JSONObject.toJSON(ResultModel.succ(name)));
    }
}
