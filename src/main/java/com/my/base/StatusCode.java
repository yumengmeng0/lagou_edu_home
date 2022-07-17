package com.my.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: ymm
 * @date: 2022/7/14
 * @version: 1.0.0
 * @description:
 */
public enum StatusCode {
    SUCCESS(0, "success"), FAIL(1, "fail");

    // 定义属性
    private int code;
    private String message;

    StatusCode() {
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 重写toString方法，将枚举对象转换为JSON
     *
     * @return
     */
    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("status", code);
        jsonObject.put("msg", message);

        return jsonObject.toString();
    }
}
