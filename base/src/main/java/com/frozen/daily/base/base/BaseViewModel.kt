package com.frozen.daily.base.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.frozen.daily.base.component.SingleLiveEvent

/**
 * Created by csc on 2018/5/14.
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val showProgress by lazy {
        SingleLiveEvent<Boolean>()
    }

    val message by lazy {
        SingleLiveEvent<String>()
    }
}