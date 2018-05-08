package com.frozen.dailys.component.im

import android.util.Log
import com.frozen.dailys.model.Info
import com.frozen.dailys.ui.message.MessageDetailContract
import com.frozen.dailys.util.extensions.toJson
import java.util.*

/**
 * Created by csc on 2018/5/2.
 */
class ChatPresenter(var view: MessageDetailContract.View) : Observer, MessageDetailContract.Presenter {

    override fun start() {
        IMObservable.newInstance().addObserver(this)
    }

    override fun stop() {
        IMObservable.newInstance().deleteObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        Log.e("update", "")
        if (arg is Info) {
            view.newMessage(arg)
        }
    }

    override fun sendInfo(info: Info) {
        IMManager.newInstance().send(info.toJson())
    }

}