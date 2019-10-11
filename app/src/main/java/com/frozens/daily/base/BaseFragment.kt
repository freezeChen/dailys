package com.frozens.daily.base

import androidx.fragment.app.Fragment
import com.frozens.daily.component.RxManage
import io.reactivex.disposables.CompositeDisposable


open class BaseFragment : Fragment(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null

    override fun onStop() {
        super.onStop()
        unDispose()
    }

}