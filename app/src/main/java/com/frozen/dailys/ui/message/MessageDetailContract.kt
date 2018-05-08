package com.frozen.dailys.ui.message

import com.frozen.dailys.base.BasePresenter
import com.frozen.dailys.base.BaseView
import com.frozen.dailys.model.Info

/**
 * Created by csc on 2018/5/2.
 */
interface MessageDetailContract {
    interface View : BaseView {
        fun newMessage(info: Info?)

        fun sendMessage(info: Info)
    }

    interface Presenter : BasePresenter {
        fun sendInfo(info: Info)
    }

}