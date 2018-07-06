package com.frozen.daily.base.component.imageloader

import android.content.Context
import android.graphics.Bitmap

/**
 * Created by csc on 2018/5/14.
 */
class ImageLoader : ImageLoaderImpl {

    private var mImageLoader: ImageLoaderImpl? = null


    companion object {
        fun getInstance() = INNER.INSTANCE
    }

    private object INNER {
        val INSTANCE = ImageLoader()
    }

    init {
        mImageLoader = GlideImageLoader()
    }


    override fun getBitmap(context: Context, url: String, listener: ImageLoaderListener<Bitmap>) {
        mImageLoader?.getBitmap(context, url, listener)
    }

    override fun clearCache(context: Context) {
        mImageLoader?.clearCache(context)
    }

    override fun showImage(context: Context, options: ImageLoaderOptions) {
        mImageLoader?.showImage(context, options)
    }
}
