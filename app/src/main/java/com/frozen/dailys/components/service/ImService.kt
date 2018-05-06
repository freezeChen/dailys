package com.frozen.dailys.components.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder


class ImService : Service() {
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

}
