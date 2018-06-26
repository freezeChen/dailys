package com.frozen.dailys.base

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable

abstract class BaseRefreshViewModel<T>(application: Application) : BaseViewModel(application) {

    val allData by lazy {
        MutableLiveData<T>()
    }

    abstract fun getData(lastData: Any?, page: Int): Disposable?


}