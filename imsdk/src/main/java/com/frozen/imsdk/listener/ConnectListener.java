package com.frozen.imsdk.listener;

public interface ConnectListener {

    public void onSuccess();

    public void onFailed();

    public void onReconnect();
}
