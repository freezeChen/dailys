package com.frozen.daily.util

import com.frozen.daily.component.SingleLiveEvent
import com.frozen.daily.component.http.ServerException
import com.frozen.daily.model.Response
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {

    fun <T> responseTransformer(): ObservableTransformer<Response<T>, Response<T>> {
        return ObservableTransformer {
            it.flatMap {
                if (it.isOk()) {
                    Observable.just(it)
                } else {
                    throw ServerException(it.code, it.msg.orEmpty())
                }
            }
        }
    }


    fun <T> progressTransformer(progress: SingleLiveEvent<Boolean>, page: Int = 1): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                    .doOnSubscribe {
                        progress.value = true
                    }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally {
                        progress.value = false
                    }
        }
    }

}
