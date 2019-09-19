package com.frozens.daily.component.network

import io.reactivex.Observable
import io.reactivex.functions.Function

class HttpResponseFunc<T> : Function<Throwable, Observable<T>> {
    override fun apply(throwable: Throwable): Observable<T> =
        Observable.error(ExceptionHandle.handleException(throwable))
}