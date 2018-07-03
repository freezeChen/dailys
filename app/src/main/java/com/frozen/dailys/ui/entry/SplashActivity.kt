package com.frozen.dailys.ui.entry

import android.os.Bundle
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseActivity
import com.frozen.dailys.util.RxManage
import com.frozen.dailys.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.startActivity

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
                            startActivity<LoginActivity>()
                            finish()
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