package com.blackiceinc.era.services.exception.model;

public class CfgMessage {
    private Type type;
    private String msg;

    public CfgMessage(Type type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public enum Type {
        WARN,
        ERROR
    }
}
