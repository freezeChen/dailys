package com.frozens.daily

import com.bumptech.glide.util.ByteBufferUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import okhttp3.*
import okio.ByteString
import org.junit.Test

import org.junit.Assert.*
import java.nio.ByteBuffer

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var mOkHttpClient: OkHttpClient
    lateinit var mSocket: WebSocket

    val mListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
//            webSocket.send()

            println("onopen")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("onfailure" + t.message)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            println("onclosing")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            println("onclosed")
        }
    }


    @Test
    fun addition_isCorrect() {
        Logger.addLogAdapter(AndroidLogAdapter())
        mOkHttpClient = OkHttpClient.Builder().build()

//        mSocket.send()

        val request = Request.Builder().url("ws:localhost:7001/socket").build()
        mSocket = mOkHttpClient.newWebSocket(request, mListener)

        mOkHttpClient.dispatcher().executorService().shutdown()

mSocket.send()
        System.`in`.read()
    }


    @Test
    fun tow2() {

    }



/*    private static byte[] IntToByte4Array(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    private static byte[] IntToByte2Array(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);

        byte[] result = new byte[2];
        result[0] = src[2];
        result[1] = src[3];

        return result;
    }

    private static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (byte aB : b) {
            value = (value << 8) | aB;
        }
        return value;

    }*/
}
