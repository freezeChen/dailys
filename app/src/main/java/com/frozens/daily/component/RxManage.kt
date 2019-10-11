package com.frozens.daily.component

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface RxManage {
    var mCompositeDisposable: CompositeDisposable?

    fun addDispose(disposable: Disposable?) {
        disposable?.let {
            if (mCompositeDisposable == null) {
                mCompositeDisposable = CompositeDisposable()
            }

            mCompositeDisposable!!.add(disposable)
        }
    }

    fun unDispose() {
        if (mCompositeDisposable != null && !mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable!!.dispose()
        }
        mCompositeDisposable = null

    }

}