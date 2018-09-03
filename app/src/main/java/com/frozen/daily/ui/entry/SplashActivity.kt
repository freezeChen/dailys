package com.frozen.daily.ui.entry

import android.os.Bundle
import android.widget.ImageView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.frozen.daily.R
import com.frozen.daily.base.base.BaseActivity
import com.frozen.daily.base.component.imageloader.ImageLoader
import com.frozen.daily.base.component.imageloader.ImageLoaderOptions
import com.frozen.daily.base.util.RxManage
import com.frozen.daily.base.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.security.auth.login.LoginException

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
                            Logger.e("onComplete")
                            ARouter.getInstance().build("/setting/main")

                                    .navigation(this, object : NavigationCallback {
                                        override fun onLost(postcard: Postcard?) {
                                            Logger.e("onLost")
                                        }

                                        override fun onFound(postcard: Postcard?) {
                                            Logger.e("onComplete")
                                        }

                                        override fun onInterrupt(postcard: Postcard?) {
                                            Logger.e("onComplete")
                                        }

                                        override fun onArrival(postcard: Postcard?) {
                                            Logger.e("onComplete")
                                        }
                                    })
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