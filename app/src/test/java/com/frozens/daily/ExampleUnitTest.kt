package com.frozens.daily

import com.frozens.daily.component.netty.NettyClient
import com.frozens.daily.component.netty.Protocol
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {


    @Test
    fun tow2() {
        NettyClient.getInstance().connect()




        System.`in`.read()
    }
}
