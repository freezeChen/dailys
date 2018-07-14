package com.frozen.daily.base.component.imageloader

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.view.View
import com.bumptech.glide.Glide

/**
 * Created by csc on 2018/5/14.
 */
internal interface ImageLoaderImpl {
    fun showImage(context: Context, options: ImageLoaderOptions)
    fun getBitmap(context: Context, url: String, listener: ImageLoaderListener<Bitmap>)
    fun clearCache(context: Context)
}