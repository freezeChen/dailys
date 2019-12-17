package com.frozens.daily.component.websocket

import com.orhanobut.logger.Logger
import okhttp3.*
import okio.ByteString
import java.util.concurrent.TimeUnit


class WebsocketClient {


    companion object {
        private var client: WebsocketClient? = null
        fun getInstance(): WebsocketClient {
            if (client == null) {
                client = WebsocketClient()
            }
            return client!!
        }
    }


    fun connect() {
        val mOkHttpClient = OkHttpClient.Builder()
            .readTimeout(3, TimeUnit.SECONDS) //设置读取超时时间
            .writeTimeout(3, TimeUnit.SECONDS) //设置写的超时时间
            .connectTimeout(3, TimeUnit.SECONDS) //设置连接超时时间
            .build()

        val request: Request = Request.Builder().url("ws://192.168.10.53:7001/socket").build()
//        val socketListener = EchoWebSocketListener()

        // 刚进入界面，就开启心跳检测
        // 刚进入界面，就开启心跳检测
//        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE)

        mOkHttpClient.newWebSocket(request, object : WebSocketListener() {
            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                Logger.i("closed")
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                Logger.i("closing")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                Logger.e("onFailure" + t.message)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                Logger.e("onmessage:$text")
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
                Logger.e("onMessage$bytes")
            }

            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                Logger.e("onOpen")
            }
        })
        mOkHttpClient.dispatcher.executorService.shutdown()
    }


}