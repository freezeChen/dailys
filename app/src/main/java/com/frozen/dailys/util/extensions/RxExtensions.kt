package com.frozen.dailys.util.extensions

import com.frozen.dailys.component.http.ExceptionHandle
import com.frozen.dailys.model.ResponseThrowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException

private val onCompleteStub: () -> Unit = {}
private val onDisposableStub: (Disposable) -> Unit = {}
private val onErrorStub: (ResponseThrowable) -> Unit = { throw OnErrorNotImplementedException(it) }
private val onNextStub: (Any) -> Unit = {}

fun <T : Any> Observable<T>.customSubscribeBy(onDisposable: (t: Disposable) -> Unit = onDisposableStub,
                                              onComplete: () -> Unit = onCompleteStub,
                                              onError: (t: ResponseThrowable) -> Unit = onErrorStub,
                                              onNext: (T) -> Unit = onNextStub
): Disposable {
    val err: (Throwable) -> Unit = {
        if (it is ResponseThrowable) onError(it)
        onError(ExceptionHandle.handleException(it))
    }
    return subscribe(onNext, err, onComplete, onDisposable)
}
