package com.frozen.imsdk;

import com.frozen.imsdk.listener.ConnectListener;
import com.frozen.imsdk.manage.IMManage;
import com.frozen.imsdk.model.IMMessage;

import org.junit.Test;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import io.netty.buffer.ByteBuf;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void netty() {
        NettyClient.getInstance().connect();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ByteBuf s = MessageDecoder.encode(2, "我是");
                    NettyClient.getInstance().insertCmd(s);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void t() {
        IMManage.getInstance().setOnConnectListener(new ConnectListener() {
            @Override
            public void onSuccess() {
                System.out.println("onsuccess");
                IMManage.getInstance().login(23);
            }

            @Override
            public void onFailed(String msg) {
                System.out.println("onfailed");
            }

            @Override
            public void onReconnect() {
                System.out.println("onreconnect");
            }
        });
        ConnectObservable.getInstance().addObserver(IMManage.getInstance());
        IMManage.getInstance().init();
//        IMManage.getInstance().login(23);

//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//        }
//
//        IMManage.getInstance().login(23);
//
//
//        IMManage.getInstance().sendMessage(new IMMessage(23,3,"我是消息啊"));



        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}



