package com.frozen.imsdk.listener;

public interface ConnectListener {

    public void onSuccess();

    public void onFailed(String msg);

    public void onReconnect();
}
