package com.dz.oa.vo;

/**
 * Created by daweizhuang on 8/30/16.
 */
public class FileUploadResponse {

    private Integer id;
    private String msg;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}