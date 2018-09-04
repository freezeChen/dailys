package com.frozen.daily.im.service

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.frozen.daily.base.component.SingleLiveEvent
import com.frozen.imsdk.listener.ConnectListener
import com.frozen.imsdk.manage.IMManage
import com.frozen.imsdk.model.IMMessage

class ImViewModel(application: Application) : AndroidViewModel(application) {

    val allMsg: MutableLiveData<MutableList<IMMessage>> = MutableLiveData()
    val online = SingleLiveEvent<Boolean>()
    val message = SingleLiveEvent<String>()

    fun start() {

        IMManage.getInstance().setOnConnectListener(object : ConnectListener {
            override fun onSuccess() {
                online.value = true
            }

            override fun onFailed(msg: String?) {
                online.value = false
                message.value = msg.orEmpty()
            }

            override fun onReconnect() {
                online.value = false
                message.value = "重新连接中"
            }

        })

        IMManage.getInstance().setOnConversationListener {
            allMsg.value?.add(it)
        }

        IMManage.getInstance().init()
        IMManage.getInstance().login(123)
    }


}