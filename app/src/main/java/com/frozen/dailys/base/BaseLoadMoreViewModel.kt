package com.frozen.dailys.base

import android.app.Application
import com.frozen.dailys.component.SingleLiveEvent

abstract class BaseLoadMoreViewModel<T>(application: Application) : BaseRefreshViewModel<T>(application) {
    val loadEnd by lazy {
        SingleLiveEvent<Boolean>()
    }


}