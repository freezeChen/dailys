package com.frozen.dailys.model

open class Response<T> {
    var errorCode: Int = 0
    var msg: String? = ""
    var data: T? = null

    fun isOk(): Boolean {
        if (errorCode == 0) return true
        return false
    }
}