package com.frozen.daily.im.ui.message.viewmodel

import android.app.Application
import com.frozen.daily.base.base.BaseLoadMoreViewModel
import com.frozen.daily.base.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.*

class MessageListViewModel(application: Application) : BaseLoadMoreViewModel<Objects>(application) {

    override fun getData(lastData: Any?, page: Int): Disposable? {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun start() {


    }


}