package com.frozen.imsdk.manage;

import android.os.Build;
import android.os.Message;

import com.frozen.imsdk.ConnectObservable;
import com.frozen.imsdk.ConversationObservable;
import com.frozen.imsdk.MessageDecoder;
import com.frozen.imsdk.NettyClient;
import com.frozen.imsdk.listener.ConnectListener;
import com.frozen.imsdk.listener.ConversationListener;
import com.frozen.imsdk.model.IMConnect;
import com.frozen.imsdk.model.IMConversation;
import com.frozen.imsdk.model.IMMessage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.netty.buffer.ByteBuf;

public class IMManage implements Observer {

    private ConnectListener mConnectListener;
    private ConversationListener mConversationListener;

    private Map<Integer, IMConversation> mConversationList = new HashMap<>();


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

    /**
     * 初始化连接
     */
    public void init() {
        NettyClient.getInstance().connect();
    }

    public void login(int id) {
        ByteBuf byteBuf = MessageDecoder.encode(MessageDecoder.OPER_CHECK, String.valueOf(id));
        NettyClient.getInstance().insertCmd(byteBuf);
    }


    public void sendMessage(IMMessage message) {
        Gson gson = new Gson();
        String json = gson.toJson(message);
        ByteBuf byteBuf = MessageDecoder.encode(MessageDecoder.OPER_MSG, json);
        NettyClient.getInstance().insertCmd(byteBuf);
    }

    private void setMessage(IMMessage message) {


        if (mConversationList.containsKey(message.getId())) {
            mConversationList.get(message.getId()).setMessage(message);
        } else {
            IMConversation conversation = new IMConversation();
            conversation.setMessage(message);
            mConversationList.put(message.getId(), conversation);
        }

        if (mConversationListener != null) {
            mConversationListener.newMessage(message);
        }
    }


    @Override
    public void update(Observable observable, Object o) {

        //连接信息
        if (observable instanceof ConnectObservable) {
            if (o instanceof IMConnect && mConnectListener != null) {
                IMConnect temp = (IMConnect) o;
                switch (temp.getStatus()) {
                    case IMConnect.CONNECT_SUCCESS:
                        mConnectListener.onSuccess();
                        break;
                    case IMConnect.CONNECT_FAILED:
                        mConnectListener.onFailed(temp.getMsg());
                        break;
                    case IMConnect.CONNECT_RECONNECT:
                        mConnectListener.onReconnect();
                        break;
                }
            }

            //消息
        } else if (observable instanceof ConversationObservable) {
            setMessage((IMMessage) o);
        }
    }


    public void setOnConnectListener(ConnectListener listener) {
        mConnectListener = listener;
    }

    public void setOnConversationListener(ConversationListener mConversationListener) {
        this.mConversationListener = mConversationListener;
    }

    public void destroy() {
        ConversationObservable.getInstance().deleteObserver(this);
    }


}
