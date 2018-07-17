package com.frozen.imsdk.manage;

import android.os.Message;

import com.frozen.imsdk.NettyClient;

import java.util.List;

public class IMMessageManage {



    private IMMessageManage() {
    }

    public static IMMessageManage getInstance() {
        return Inner.INSTANCE;
    }

    static class Inner {
        public static IMMessageManage INSTANCE = new IMMessageManage();
    }

    public List<Message> getConversation() {
        return null;
    }

}
