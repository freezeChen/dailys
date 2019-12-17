package com.frozens.daily.component.netty;

public class IMConnect {
    public static final int Status_connect_success = 1;
    public static final int Status_connect_failed = 2;
    public static final int status_reconnect=3;

    private int status;

    public IMConnect(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
