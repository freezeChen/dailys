package com.frozen.dailys.components.util.extensions

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

private val onCompleteStub: () -> Unit = {}
private val onDisposableStub: (Disposable) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onNextStub: (Any) -> Unit = {}

fun <T : Any> Observable<T>.customsubscribeby(onDisposable: (t: Disposable) -> Unit = onDisposableStub,
                                              onComplete: () -> Unit = onCompleteStub,
                                              onError: (t: Throwable) -> Unit = onErrorStub,
                                              onNext: (T) -> Unit = onNextStub
) {
    subscribe(onNext, onError, onComplete, onDisposable)
}
