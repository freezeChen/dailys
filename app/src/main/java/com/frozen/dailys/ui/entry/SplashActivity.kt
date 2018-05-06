package com.frozen.dailys.ui.entry

import android.os.Bundle
import android.os.PersistableBundle
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseActivity
import io.reactivex.Observable

class SplashActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)

        val times = 2
        Observable.just(2)
                .flatMap {
                    Observable.just(times - it)
                }
                .doOnSubscribe { }
    }
}