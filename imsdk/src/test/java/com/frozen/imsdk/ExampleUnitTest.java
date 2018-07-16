package com.frozen.imsdk;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void netty() {
        NettyClient.getInstance().connect();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    NettyClient.getInstance().insertCmd("我是客户端");
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

}


