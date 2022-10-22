package com.pratice.shopcar.util;

import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int state;//返回状态码
    private String message;//状态码回复信息
    private T data;//返回数据
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
