package com.frozen.daily.base.component.imageloader

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * Created by csc on 2018/5/14.
 */
interface ImageLoaderListener<T> : RequestListener<T> {
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<T>?, isFirstResource: Boolean): Boolean {
        return Failed(e, model, target, isFirstResource)
    }

    override fun onResourceReady(resource: T, model: Any?, target: Target<T>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
        return success(resource, model, target, dataSource, isFirstResource)
    }

    abstract fun success(resource: T, model: Any?, target: Target<T>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean
    abstract fun Failed(e: Exception?, model: Any?, target: Target<T>?, isFirstResource: Boolean): Boolean
}