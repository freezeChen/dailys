package com.frozen.imsdk;

import com.frozen.imsdk.listener.ConnectListener;
import com.frozen.imsdk.manage.IMManage;
import com.frozen.imsdk.model.IMMessage;

import org.junit.Test;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import io.netty.buffer.ByteBuf;

import static org.junit.Assert.*;

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
        ByteBuf s = MessageDecoder.encode(0, "我是");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {


        }
        NettyClient.getInstance().insertCmd(s);
        NettyClient.getInstance().insertCmd(s);
        NettyClient.getInstance().insertCmd(s);
        NettyClient.getInstance().insertCmd(s);
        NettyClient.getInstance().insertCmd(s);


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        NettyClient.getInstance().insertCmd(s.copy());
//        NettyClient.getInstance().insertCmd(s.copy());
//        NettyClient.getInstance().insertCmd(s.copy());
    }

    @Test
    public void ob() {

        Observer observer = new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                System.out.println("uuuuuuuuu");
            }
        };

        Observable observable = new Observable() {
            @Override
            protected synchronized void setChanged() {
                super.setChanged();
            }
        };
//
        observable.addObserver(observer);

        observable.notifyObservers("fssss");

        boolean b = observable.hasChanged();
        System.out.println(b);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ConnectObservable.getInstance().addObserver(observer);
//
//        ConnectObservable.getInstance().notifyObservers(123);
//        ConnectObservable.getInstance().notify();

    }


}



