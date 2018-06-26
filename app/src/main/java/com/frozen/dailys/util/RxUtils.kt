package com.frozen.dailys.util

import com.frozen.dailys.component.http.ServerException
import com.frozen.dailys.model.Response
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class RxUtils {
    companion object {
        fun <T> responseTransformer(): ObservableTransformer<Response<T>, T> {
            return ObservableTransformer {
                it.flatMap {
                    if (it.isOk()) {
                        Observable.just(it.data)
                    } else {
                        throw ServerException(it.errorCode, it.msg.orEmpty())
                    }
                }
            }
        }
    }
}
