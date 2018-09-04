package com.frozen.daily.im.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class ImService : Service() {
    private lateinit var mViewModel: ImViewModel

    override fun onBind(p0: Intent?): IBinder {
        return MBinder(mViewModel)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mViewModel = ImViewModel(this.application)
        mViewModel.start()

        return super.onStartCommand(intent, flags, startId)
    }

    inner class MBinder(val viewModel: ImViewModel) : Binder() {}
}