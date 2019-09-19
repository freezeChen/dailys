package com.frozens.daily.entity

data class Response<T>(
    val code: Int,
    val msg: String,
    val data: T
) {
    fun isOk(): Boolean {
        return this.code == 0
    }
}