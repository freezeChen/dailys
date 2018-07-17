package com.frozen.daily.im.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.frozen.imsdk.NettyClient

class ImService : Service() {
    override fun onBind(p0: Intent?): IBinder {

        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        NettyClient.getInstance().connect()


        return super.onStartCommand(intent, flags, startId)
    }

}