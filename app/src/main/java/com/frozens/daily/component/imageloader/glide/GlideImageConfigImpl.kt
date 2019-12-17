package com.frozens.daily.component.imageloader.glide

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.request.RequestListener
import com.frozens.daily.component.imageloader.ImageConfig
import com.frozens.daily.component.imageloader.ImageLoaderClearListener
import com.frozens.daily.component.imageloader.ImageLoaderListener
//import cn.com.bethink.cloudlabelwms.utils.getNonEmptyString


/**
 * Created by xiang23 on 2018/3/22.
 */
class GlideImageConfigImpl : ImageConfig {
    private val cacheStrategy: Int//0对应DiskCacheStrategy.all,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
    private val fallback: Int //请求 url 为空,则使用此图片作为占位符
    private val transformation: Transformation<Bitmap>?//glide用它来改变图形的形状
    private val imageViews: Array<out ImageView>?
    private val isClearMemory: Boolean//清理内存缓存
    private val isClearDiskCache: Boolean//清理本地缓存
    private val openTransition: Boolean //fade过渡动画
    private val isCircle: Boolean
    private val requestListener: RequestListener<Drawable>?
    private val imageLoaderListener: ImageLoaderListener?
    private val skipMemoryCache: Boolean
    private val imageLoaderClearListener: ImageLoaderClearListener?


    constructor(builder: Builder) : super(builder.url, builder.resId, builder.imageView, builder.placeholder, builder.errorPic) {
        this.fallback = builder.fallback
        this.cacheStrategy = builder.cacheStrategy
        this.transformation = builder.transformation
        this.imageViews = builder.imageViews
        this.isClearMemory = builder.isClearMemory
        this.isClearDiskCache = builder.isClearDiskCache
        this.openTransition = builder.openTransition
        this.isCircle = builder.isCircle
        this.requestListener = builder.requestListener
        this.imageLoaderListener = builder.imageLoaderListener
        this.skipMemoryCache = builder.skipMemoryCache
        this.imageLoaderClearListener = builder.imageLoaderClearListener
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    fun getCacheStrategy(): Int {
        return cacheStrategy
    }

    fun getTransformation(): Transformation<Bitmap>? {
        return transformation
    }

    fun getImageViews(): Array<out ImageView>? {
        return imageViews
    }

    fun isClearMemory(): Boolean {
        return isClearMemory
    }

    fun isClearDiskCache(): Boolean {
        return isClearDiskCache
    }

    fun isCircle(): Boolean {
        return isCircle
    }

    fun getFallback(): Int {
        return fallback
    }

    fun isOpenTransition(): Boolean {
        return openTransition
    }

    fun getRequestListener(): RequestListener<Drawable>? {
        return requestListener
    }

    fun getImageLoaderListener(): ImageLoaderListener? {
        return imageLoaderListener
    }

    fun getImageLoaderClearListener(): ImageLoaderClearListener? {
        return imageLoaderClearListener
    }


    fun getSkipMemoryCache(): Boolean {
        return skipMemoryCache
    }


    class Builder {
        var url: String = ""
        var resId: Int = 0
        var imageView: ImageView? = null
        var placeholder: Int = 0
        var errorPic: Int = 0
        var fallback: Int = 0 //请求 url 为空,则使用此图片作为占位符
        var cacheStrategy: Int = 0//0对应DiskCacheStrategy.all,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
        var transformation: Transformation<Bitmap>? = null//glide用它来改变图形的形状
        var imageViews: Array<out ImageView>? = null
        var isClearMemory: Boolean = false//清理内存缓存
        var isClearDiskCache: Boolean = false//清理本地缓存
        var isCircle: Boolean = false
        var openTransition: Boolean = true
        var requestListener: RequestListener<Drawable>? = null

        var imageLoaderListener: ImageLoaderListener? = null

        var imageLoaderClearListener: ImageLoaderClearListener? = null

        var skipMemoryCache: Boolean = false

        fun url(url: String?): Builder {
            this.url = url.orEmpty()
            return this
        }


        fun resId(resId: Int?): Builder {
            if (resId != null) {
                this.resId = resId
            }
            return this
        }

        fun placeholder(placeholder: Int): Builder {
            this.placeholder = placeholder
            return this
        }

        fun errorPic(errorPic: Int): Builder {
            this.errorPic = errorPic
            return this
        }

        fun fallback(fallback: Int): Builder {
            this.fallback = fallback
            return this
        }

        fun imageView(imageView: ImageView): Builder {
            this.imageView = imageView
            return this
        }

        fun cacheStrategy(cacheStrategy: Int): Builder {
            this.cacheStrategy = cacheStrategy
            return this
        }

        fun transformation(transformation: Transformation<Bitmap>): Builder {
            this.transformation = transformation
            return this
        }

        fun imageViews(vararg imageViews: ImageView): Builder {
            this.imageViews = imageViews
            return this
        }

        fun isClearMemory(isClearMemory: Boolean): Builder {
            this.isClearMemory = isClearMemory
            return this
        }

        fun isClearDiskCache(isClearDiskCache: Boolean): Builder {
            this.isClearDiskCache = isClearDiskCache
            return this
        }

        fun isCircle(isCircle: Boolean): Builder {
            this.isCircle = isCircle
            return this
        }

        fun openTransition(openTransition: Boolean): Builder {
            this.openTransition = openTransition
            return this
        }

        fun requestListener(requestListener: RequestListener<Drawable>): Builder {
            this.requestListener = requestListener
            return this
        }

        fun imageLoaderListener(imageLoaderListener: ImageLoaderListener): Builder {
            this.imageLoaderListener = imageLoaderListener
            return this
        }

        fun imageLoaderClearListener(imageLoaderClearListener: ImageLoaderClearListener): Builder {
            this.imageLoaderClearListener = imageLoaderClearListener
            return this
        }


        fun skipMemoryCache(skipMemoryCache: Boolean): Builder {
            this.skipMemoryCache = skipMemoryCache
            return this
        }


        fun build(): GlideImageConfigImpl {
            return GlideImageConfigImpl(this)
        }
    }

}