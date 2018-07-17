package com.frozen.imsdk.model;

public class IMConnect {
    public static final int CONNECT_SUCCESS = 1;
    public static final int CONNECT_ING = 2;
    public static final int CONNECT_FAILED = 3;
    public static final int CONNECT_RECONNECT = 4;

    public IMConnect(int status) {
        this.status = status;
    }

    private int status = 0;
}
