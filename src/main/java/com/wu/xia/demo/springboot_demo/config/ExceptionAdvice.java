package com.wu.xia.demo.springboot_demo.config;

import com.wu.xia.demo.springboot_demo.api.Resp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 其他异常拦截
     * @param ex 异常
     * @param request 请求参数
     * @return 接口响应
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Resp handleException(Exception ex, HttpServletRequest request) {
        log.error("request {} throw Exception \n", request, ex);
        return Resp.failure(ex.getMessage());
    }
}