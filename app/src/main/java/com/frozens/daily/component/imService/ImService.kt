package com.frozens.daily.component.imService

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.frozens.daily.component.EventBusSingleton
import com.frozens.daily.component.imService.event.ConnectStateEvent
import com.frozens.daily.component.imService.event.MsgEvent
import com.frozens.daily.component.netty.IMConnect
import com.frozens.daily.component.netty.MessageDecoder
import com.frozens.daily.component.netty.NettyClient
import com.frozens.daily.component.netty.Protocol
import com.frozens.daily.component.netty.observable.ConnectObservable
import com.frozens.daily.component.netty.observable.MessageObservable
import com.orhanobut.logger.Logger

import java.util.*
import java.util.concurrent.TimeUnit


class ImService : Service(), Observer {
    override fun update(o: Observable, arg: Any?) {
        when (arg) {
            is Protocol -> {
                when (arg.protocol) {
                    Protocol.Op_AuthReply -> {
                        heardBeat()
                    }
                    else -> {
                    }
                }


                EventBusSingleton.getInstance().post(MsgEvent(arg))
                Logger.i("onMessage")
            }
            is Int -> {
                
                when (arg) {

                    IMConnect.Status_connect_success -> {
                        auth()
                    }
                    IMConnect.status_reconnect -> {

                    }
                    IMConnect.Status_connect_failed -> {

                    }

                    else -> {
                    }
                }



                EventBusSingleton.getInstance().post(ConnectStateEvent(arg))
            }
            else -> {
                Logger.i("${arg?.javaClass}")
            }
        }


    }

    private fun heardBeat() {
        io.reactivex.Observable.interval(0, 5, TimeUnit.MINUTES)
            .subscribe {
                NettyClient.getInstance()
                    .insertCmd(MessageDecoder.encode(Protocol.Op_Heartbeat, ""))
            }
    }


    override fun onCreate() {
        super.onCreate()

        MessageObservable.getInstance().addObserver(this)
        ConnectObservable.getInstance().addObserver(this)
        NettyClient.getInstance().connect()

    }

    fun auth() {
        Logger.i("auth")
        NettyClient.getInstance().insertCmd(MessageDecoder.encode(Protocol.OP_AUTH, "1001"))
    }


    override fun onBind(intent: Intent?): IBinder? {
        return IMBinder()
    }


    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    inner class IMBinder : Binder() {
        fun getService(): ImService {
            return this@ImService
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        MessageObservable.getInstance().deleteObserver(this)
        ConnectObservable.getInstance().deleteObserver(this)
    }

}