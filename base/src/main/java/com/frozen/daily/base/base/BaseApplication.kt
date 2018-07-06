package com.frozen.daily.base.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.frozen.daily.base.BuildConfig

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

import me.yokeyword.fragmentation.Fragmentation

/**
 * Created by csc on 2018/5/8.
 */
class BaseApplication : Application() {

    companion object {
        lateinit var mBaseApplicationContext: Context

    }

    override fun onCreate() {
        super.onCreate()
        mBaseApplicationContext = applicationContext
        initFragmentation()
        initLog()

        ARouter.openDebug()
        ARouter.openLog()

        ARouter.init(this)
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