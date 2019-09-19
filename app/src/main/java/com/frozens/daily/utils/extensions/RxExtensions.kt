package com.frozens.daily.utils.extensions


import com.frozens.daily.component.network.ExceptionHandle
import com.frozens.daily.component.network.ResponseThrowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.OnErrorNotImplementedException


/**
 * Created by chx on 2017/9/25.
 */


private val onNextStub: (Any) -> Unit = {}
private val onBaseErrorStub: (ResponseThrowable) -> Unit =
    { throw OnErrorNotImplementedException(it) }
private val onCompleteStub: () -> Unit = {}
private val onSubscribeStub: (dispose: Disposable) -> Unit = {}

fun <T : Any> Observable<T>.customSubscribeBy(
    onBaseError: (ResponseThrowable) -> Unit = onBaseErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onNext: (T) -> Unit = onNextStub,
    onSubscribe: (dispose: Disposable) -> Unit = onSubscribeStub
): Disposable {


    val onErrorStub: (Throwable) -> Unit = {
        if (it is ResponseThrowable) {
            onBaseError(it)
        } else {
            onBaseError(ExceptionHandle.handleException(it))
        }
    }
    return subscribe(onNext, onErrorStub, onComplete, onSubscribe)
}


fun Disposable?.safeDispose() {
    if (this != null && !this.isDisposed) {
        this.dispose()
    }
}