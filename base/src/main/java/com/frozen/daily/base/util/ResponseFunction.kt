package com.frozen.daily.base.util

import com.frozen.daily.base.component.http.ExceptionHandle
import io.reactivex.Observable
import io.reactivex.functions.Function

class ResponseFunction<T> : Function<Throwable, Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
        return Observable.error(ExceptionHandle.handleException(t))
    }
}