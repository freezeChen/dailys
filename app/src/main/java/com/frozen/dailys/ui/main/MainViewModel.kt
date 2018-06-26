package com.frozen.dailys.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.frozen.dailys.base.BaseLoadMoreViewModel
import com.frozen.dailys.base.BaseViewModel
import com.frozen.dailys.component.AppData
import com.frozen.dailys.model.Article
import com.frozen.dailys.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : BaseLoadMoreViewModel<Article>(application) {


    override fun getData(lastData: Any?, page: Int): Disposable? {
        Logger.e("getdata")

        return AppData.getArticleList(0)
                .doOnSubscribe { showProgress.value =true }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .customSubscribeBy(
                        onError = {
                            message.value = it.customMessage
                            showProgress.value = false
                        },
                        onComplete = {
                            showProgress.value = false
                        },
                        onNext = {
                            Logger.e(it.toString())
                            allData.value = it
                        }
                )
    }
}
