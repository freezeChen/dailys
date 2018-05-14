package com.frozen.dailys.base

import android.app.Application
import android.content.Context
import com.frozen.dailys.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogAdapter
import com.orhanobut.logger.Logger

import me.yokeyword.fragmentation.Fragmentation

class BaseApplication : Application() {

    companion object {
        lateinit var mBaseApplicationContext: Context

    }

    override fun onCreate() {
        super.onCreate()
        mBaseApplicationContext = applicationContext
        initFragmentation()
        initLog()
    }

    private fun initLog() {
        if (BuildConfig.DEBUG_MODE)
            Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(com.frozen.dailys.BuildConfig.DEBUG_MODE)
                .install()
    }
}