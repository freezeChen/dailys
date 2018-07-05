package com.frozen.daily.component.im

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import okhttp3.WebSocket

/**
 * Created by csc on 2018/5/2.
 * 负责连接
 */
class IMManager constructor(application: Application) : AndroidViewModel(application) {

    private val webSocket by lazy {
        MutableLiveData<WebSocket>()
    }

    fun start() {

//        BaseApplication.mBaseApplicationContext.startService<IMService>()
    }


    companion object {
        private var INSTANCE: IMManager? = null
        fun getInstance(application: Application): IMManager {
            return INSTANCE ?: synchronized(IMManager::class.java) {
                INSTANCE ?: IMManager(application).also {
                    INSTANCE = it
                }
            }
        }
    }


}