package com.frozen.dailys.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.frozen.dailys.component.SingleLiveEvent
import io.reactivex.disposables.Disposable

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