package com.frozen.daily.base.base

import com.frozen.daily.base.util.RxManage
import io.reactivex.disposables.CompositeDisposable
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.progressDialog
/**
 * Created by csc on 2018/5/8.
 */
open class BaseFragment : SupportFragment(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null

    protected val progress by lazy {
        _mActivity.progressDialog("加载中")
    }

    override fun onDestroy() {
        unDisposable()
        super.onDestroy()
    }
}