package com.atlp.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

// 自定义统一格式返回JSON结果类
@Data
public class R {

    // 返回是否成功
    private Boolean success;
    // 返回状态码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    private R() {}

    // 成功方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCodeEnum.SUCCESS.getKey());
        r.setMessage(ResultCodeEnum.SUCCESS.getValue());
        return r;
    }

    // 失败方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCodeEnum.ERROR.getKey());
        r.setMessage(ResultCodeEnum.ERROR.getValue());
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
