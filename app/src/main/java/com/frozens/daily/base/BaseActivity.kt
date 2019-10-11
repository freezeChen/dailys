package com.frozens.daily.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.frozens.daily.component.RxManage
import io.reactivex.disposables.CompositeDisposable

@SuppressLint("Registered")
open class BaseActivity() : AppCompatActivity(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onStop() {
        super.onStop()
        unDispose()
    }


}