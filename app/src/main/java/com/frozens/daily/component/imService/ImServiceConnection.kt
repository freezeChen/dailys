package com.frozens.daily.component.imService

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

class ImServiceConnection : ServiceConnection {
    override fun onServiceDisconnected(name: ComponentName?) {

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
    }
}