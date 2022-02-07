package com.example.demo.common;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/1/8 19:54
 * @Description 公共的返回类
 **/
public class AjaxResult <T>{

    private Integer code;
    private String message;
    private T data;

    public AjaxResult() {
        this.setCode(HttpStatus.OK.value());
        this.setMessage(HttpStatus.OK.getReasonPhrase());
        this.data = null;
    }

    public AjaxResult(T t) {
        this.setCode(HttpStatus.OK.value());
        this.setMessage(HttpStatus.OK.getReasonPhrase());
        this.data = t;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 序列化对象
     *
     * @return
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}

