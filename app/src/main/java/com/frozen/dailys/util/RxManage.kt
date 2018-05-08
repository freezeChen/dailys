package com.frozen.dailys.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface RxManage {
    var mCompositeDisposable: CompositeDisposable?

    fun addDisposable(disposable: Disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable!!.add(disposable)
    }

    fun unDisposable() {
        if (mCompositeDisposable != null && !mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable!!.dispose()
        }

    }


}