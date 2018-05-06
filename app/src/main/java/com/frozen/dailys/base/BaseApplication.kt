package com.frozen.dailys.base

import android.app.Application
import android.content.Context

import me.yokeyword.fragmentation.Fragmentation

class BaseApplication : Application() {

    companion object {
        lateinit var mBaseApplicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mBaseApplicationContext = applicationContext
        initFragmentation()
    }

    private fun initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(com.frozen.dailys.BuildConfig.DEBUG_MODE)
                .install()
    }
}