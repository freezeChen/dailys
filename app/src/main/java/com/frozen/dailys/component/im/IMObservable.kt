package com.frozen.dailys.component.im

import com.frozen.dailys.model.Info
import com.google.gson.Gson
import java.util.*


/**
 * Created by csc on 2018/5/2.
 */
class IMObservable : Observable() {

    companion object {
        fun newInstance() = Inner.INSTANCE
    }

    private object Inner {
        val INSTANCE = IMObservable()

    }

    fun setNewData(string: String) {
        val gson = Gson()
        var fromJson = gson.fromJson<Info>(string, Info::class.java)
        setChanged()
        notifyObservers(fromJson)
    }

}