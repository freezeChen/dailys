package com.frozen.daily.base.component.imageloader

import android.content.Context
import android.graphics.Bitmap

object ImageLoader : ImageLoaderImpl {
    override fun getBitmap(context: Context, url: String, listener: ImageLoaderListener<Bitmap>) {
        GlideImageLoader.getInstance().getBitmap(context, url, listener)
    }

    override fun clearCache(context: Context) {
        GlideImageLoader.getInstance().clearCache(context)
    }

    override fun showImage(context: Context, options: ImageLoaderOptions) {
        GlideImageLoader.getInstance().showImage(context, options)
    }
}