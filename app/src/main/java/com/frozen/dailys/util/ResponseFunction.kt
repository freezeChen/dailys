package com.frozen.dailys.util

import com.frozen.dailys.component.http.ExceptionHandle
import io.reactivex.Observable
import io.reactivex.functions.Function

class ResponseFunction<T> : Function<Throwable, Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
        return Observable.error(ExceptionHandle.handleException(t))
    }
}