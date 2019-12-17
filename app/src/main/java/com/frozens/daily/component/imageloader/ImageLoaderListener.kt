package com.frozens.daily.component.imageloader

import android.graphics.Bitmap
import com.bumptech.glide.request.transition.Transition

interface ImageLoaderListener {
    fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?)

    fun onError(errorString: String? = null, error: Exception? = null)
}