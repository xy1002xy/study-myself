package com.study.myself.xycommon.handler;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: xy-parent
 * @description: 请求拦截
 * @author: wxy
 * @create: 2020-12-01 16:32
 **/
public class RequestWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws
        ServletException, IOException {
        filterChain.doFilter(new ContentCachingRequestWrapper(httpServletRequest), httpServletResponse);
    }
}
