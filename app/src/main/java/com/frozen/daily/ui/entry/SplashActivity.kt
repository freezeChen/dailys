package com.frozen.daily.ui.entry

import android.os.Bundle
import com.frozen.daily.R
import com.frozen.daily.base.BaseActivity
import com.frozen.daily.util.RxManage
import com.frozen.daily.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val times = 2

        Observable.just(2)
                .customSubscribeBy(
                        onError = {
                            Logger.e(it.message)

                        },
                        onComplete = {

                        },
                        onDisposable = {
                            addDisposable(it)
                        },
                        onNext = {

                        })
    }

    override fun onDestroy() {
        unDisposable()
        super.onDestroy()
    }
}