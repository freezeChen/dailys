package com.frozen.daily.ui.entry

import android.os.Bundle
import android.widget.ImageView
import com.frozen.daily.R
import com.frozen.daily.base.base.BaseActivity
import com.frozen.daily.base.component.imageloader.ImageLoader
import com.frozen.daily.base.component.imageloader.ImageLoaderOptions
import com.frozen.daily.base.util.RxManage
import com.frozen.daily.base.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

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
                            val options = ImageLoaderOptions.Builder(ImageView(this), "")

                                    .build()
                            ImageLoader.showImage(this, options)
                        })
    }

    override fun onDestroy() {
        unDisposable()
        super.onDestroy()
    }
}