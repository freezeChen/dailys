package com.frozen.daily.base

import android.app.Application
import com.frozen.daily.component.SingleLiveEvent

abstract class BaseLoadMoreViewModel<T>(application: Application) : BaseRefreshViewModel<T>(application) {
    val loadEnd by lazy {
        SingleLiveEvent<Boolean>()
    }


}