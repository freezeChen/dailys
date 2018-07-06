package com.frozen.daily.base.base

import android.app.Application
import com.frozen.daily.base.component.SingleLiveEvent
/**
 * Created by csc on 2018/5/8.
 */
abstract class BaseLoadMoreViewModel<T>(application: Application) : BaseRefreshViewModel<T>(application) {
    val loadEnd by lazy {
        SingleLiveEvent<Boolean>()
    }


}