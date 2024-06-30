package com.wu.xia.demo.springboot_demo.api;

public class Resp {
    private int code;
    private Object data;
    private String msg;

    enum StatusEnum{
        SUCCESS(200),
        FAILURE(500);
        int code;
        StatusEnum(int code) {
            this.code = code;
        }
    }


    public Resp() {
    }

    public Resp(int code) {
        this.code = code;
    }

    Resp(int code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static Resp success(Object data) {
        Resp resp = new Resp(StatusEnum.SUCCESS.code);
        resp.setData(data);
        return resp;
    }
    public static Resp success(Object data, String msg) {
        Resp resp = new Resp(StatusEnum.SUCCESS.code);
        resp.setData(data);
        resp.setMsg(msg);
        return resp;
    }

    public static Resp failure(String msg) {
        Resp resp = new Resp(StatusEnum.FAILURE.code);
        resp.setMsg(msg);
        return resp;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
