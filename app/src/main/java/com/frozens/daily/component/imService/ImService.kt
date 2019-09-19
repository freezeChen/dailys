package com.frozens.daily.component.imService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import okhttp3.*
import okio.ByteString


class ImService : Service() {
    lateinit var mOkHttpClient: OkHttpClient
    lateinit var mSocket: WebSocket


    val mListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)

        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
        }
    }


    override fun onCreate() {
        super.onCreate()
        mOkHttpClient = OkHttpClient.Builder().build()


        val request = Request.Builder().url("ws:localhost:8080/socket").build()
        mSocket = mOkHttpClient.newWebSocket(request, mListener)

        mOkHttpClient.dispatcher().executorService().shutdown()

    }


    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

}