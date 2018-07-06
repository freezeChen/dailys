package com.frozen.daily.base.component.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.frozen.daily.base.component.im.IMManager
import com.frozen.daily.base.util.RxManage
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import okhttp3.*
import okio.ByteString


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class IMService : Service(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null
    private var reConnTimes = 0
    private var mWebSocket: WebSocket? = null
    private lateinit var mListener: WebSocketListener

    private val mClient by lazy {
        OkHttpClient()
    }
    private val request by lazy {
        Request.Builder()
                .url("ws://192.168.10.106:8088/daily/socket")
                .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mListener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket?, response: Response?) {
                super.onOpen(webSocket, response)
                Logger.e("onOpen", response)
                mWebSocket = webSocket
                reConnTimes = 0
                IMManager
            }

            override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
                super.onFailure(webSocket, t, response)
                webSocket?.cancel()
                mWebSocket = null
                Logger.e("onFailure$t")
                reConnTimes++
                if (reConnTimes <= 5) {
                    mClient.newWebSocket(request, mListener)
                }
            }

            override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
                super.onClosing(webSocket, code, reason)
                Logger.e("onclosing$reason")
            }

            override fun onMessage(webSocket: WebSocket?, text: String?) {
                super.onMessage(webSocket, text)
                Logger.e("onmessage:$text")
            }

            override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
                super.onMessage(webSocket, bytes)
                Logger.e("onmessage:${bytes.toString()}")
            }

            override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
                super.onClosed(webSocket, code, reason)
                Logger.e("onclosed", reason)
            }
        }

        mClient.newWebSocket(request, mListener)
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        unDisposable()
        super.onDestroy()

        mClient.dispatcher().executorService().shutdown()
    }

}
