package com.spring.mvc.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(
        //制定拦截包的控制器
        basePackages = { "com.spring.mvc.rest.controller.*" },
        //限定被标注为 @Controller 或者 @RestController 的类才被拦截
        annotations = { Controller.class, RestController.class })
public class VoControllerAdvice {
    //异常处理，可以定义异常类型进行拦截处理
    @ExceptionHandler(value = NotFoundExceptioin.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> exception(HttpServletRequest request, NotFoundExceptioin notFoundExceptioin) {
        Map<String, Object> msgMap = new HashMap<>();
        //获取异常信息
        msgMap.put("code", notFoundExceptioin.getCode());
        msgMap.put("message", notFoundExceptioin.getCustomMsg());
        return msgMap;
    }
}
