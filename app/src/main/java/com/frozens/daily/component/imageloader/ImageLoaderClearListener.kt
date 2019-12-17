package com.frozens.daily.component.imageloader

interface ImageLoaderClearListener {
    fun onSuccess()

    fun onError(msg: String = "", e: Exception? = null)
}