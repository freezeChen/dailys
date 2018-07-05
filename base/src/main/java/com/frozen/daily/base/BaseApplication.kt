package com.frozen.daily.base

import android.app.Application
import android.content.Context

import com.orhanobut.logger.AndroidLogAdapter
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
                .debug(BuildConfig.DEBUG_MODE)
                .install()
    }
}