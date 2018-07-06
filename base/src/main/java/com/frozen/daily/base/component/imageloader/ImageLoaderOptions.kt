package com.frozen.daily.base.component.imageloader

import android.support.annotation.DrawableRes
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.target.Target

class ImageLoaderOptions private constructor(val builder: Builder) {
    internal var mImageView: View? = null
    internal var mUrl: String? = null
    internal var mResource: Int? = null
    internal var mErrDrawable: Int = -1
    internal var mPlaceholder: Int = -1
    internal var mISCircle: Boolean = false
    internal var mDiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
    internal var mBitmapTransformation: BitmapTransformation? = null

    init {
        builder.let {
            mImageView = it.mImageView
            mUrl = it.mUrl
            mResource = it.mResource
            mErrDrawable = it.mErrDrawable
            mPlaceholder = it.mPlaceholder
            mISCircle = it.mISCircle
            mDiskCacheStrategy = it.mDiskCacheStrategy
            mBitmapTransformation = it.mBitmapTransformation
        }
    }

    class Builder {
        internal var mImageView: View? = null
        internal var mUrl: String? = null
        internal var mResource: Int? = null
        internal var mErrDrawable: Int = -1
        internal var mPlaceholder: Int = -1
        internal var mISCircle: Boolean = false
        internal var mDiskCacheStrategy = DiskCacheStrategy.AUTOMATIC
        internal var mBitmapTransformation: BitmapTransformation? = null

        constructor(v: View, url: String) {
            mImageView = v
            mUrl = url
        }

        constructor(v: View, resource: Int) {
            mImageView = v
            mResource = resource
        }

        fun errDrawable(@DrawableRes drawable: Int): Builder {
            mErrDrawable = drawable
            return this
        }

        fun placeholder(@DrawableRes drawable: Int): Builder {
            mPlaceholder = drawable
            return this
        }

        fun isCircle(): Builder {
            mISCircle = true
            return this
        }

        fun customTransformation(transformation: BitmapTransformation): Builder {
            mBitmapTransformation = transformation
            return this
        }


        fun build(): ImageLoaderOptions {
            return ImageLoaderOptions(this)
        }
    }
}