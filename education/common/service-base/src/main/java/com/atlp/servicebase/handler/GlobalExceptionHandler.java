package com.atlp.servicebase.handler;

import com.atlp.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 自定义统一异常处理类
@ControllerAdvice
@Slf4j // 标识使用Logback日志注解
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) // 标识处理异常类型注解
    @ResponseBody
    // 全局异常处理
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("执行了全局异常处理...");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    // 特定异常处理
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了特定异常处理...");
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    // 自定义异常处理
    public R error(CustomException e) {
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
