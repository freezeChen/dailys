package com.frozen.dailys.ui.message.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.frozen.dailys.base.BaseApplication
import com.frozen.dailys.base.BaseViewModel
import com.frozen.dailys.model.Info

/**
 * Created by csc on 2018/5/14.
 */
class MessageDetailViewModel(application: BaseApplication) : BaseViewModel(application) {
    private val datas: MutableLiveData<ArrayList<Info>> by lazy {
        MutableLiveData<ArrayList<Info>>()
    }

}