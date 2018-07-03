package com.frozen.dailys.component

import com.frozen.dailys.model.Info
import com.frozen.dailys.model.User

/**
 * Created by csc on 2018/5/2.
 */
class DataLab {
    companion object {
        fun getInstance() = Inner.INSTANCE
    }

    private object Inner {
        val INSTANCE = DataLab()
    }

    var user: User? = null

}