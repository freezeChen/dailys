package com.frozen.imsdk.manage;

import android.os.Message;

import com.frozen.imsdk.ConversationObservable;
import com.frozen.imsdk.listener.ConnectListener;
import com.frozen.imsdk.model.IMConversation;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class IMManage implements Observer {

    private ConnectListener mConnectListener;

    private IMManage() {
        ConversationObservable.getInstance().addObserver(this);
    }

    public static IMManage getInstance() {
        return Inner.INSTANCE;
    }


    static class Inner {
        public static IMManage INSTANCE = new IMManage();
    }

    public List<Message> getConversation() {
        return null;
    }


    @Override
    public void update(Observable observable, Object o) {

    }


    public void setOnConnectListener(ConnectListener listener) {
        mConnectListener = listener;
    }


}
