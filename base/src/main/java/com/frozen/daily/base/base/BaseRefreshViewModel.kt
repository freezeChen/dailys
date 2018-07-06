package com.frozen.daily.base.base

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.Disposable
/**
 * Created by csc on 2018/5/8.
 */
abstract class BaseRefreshViewModel<T>(application: Application) : BaseViewModel(application) {

    val allData by lazy {
        MutableLiveData<T>()
    }

    abstract fun getData(lastData: Any?, page: Int): Disposable?


}