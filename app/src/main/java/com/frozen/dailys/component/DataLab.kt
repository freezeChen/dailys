package com.frozen.dailys.component

import com.frozen.dailys.model.Info

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

    var user: Info? = null

}