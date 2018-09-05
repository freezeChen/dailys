package com.frozen.imsdk.model;

public class IMMessage {
    public IMMessage(int id, int sid, String msg) {
        this.id = id;
        this.sid = sid;
        this.msg = msg;
    }

    private int id;
    private int sid;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
