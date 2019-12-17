package com.frozens.daily.component.imageloader.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
//import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.frozens.daily.component.imageloader.BaseImageLoaderStrategy
import com.frozens.daily.utils.extensions.customSubscribeBy

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java.io.File


/**
 * Created by xiang23 on 2018/3/22.
 */
class GlideImageLoaderStrategy : BaseImageLoaderStrategy<GlideImageConfigImpl>,
    GlideAppliesOptions {

    override fun loadImage(ctx: Context?, config: GlideImageConfigImpl?) {
        if (ctx == null) return
        if (config == null) return
        if (TextUtils.isEmpty(config.url) && config.resID == 0) return
        if (config.imageView == null) return

        //有资源图片载入资源图片 没有才载入网络图片
        var glideRequest = when {
            config.resID != 0 -> GlideApp.with(ctx).load(config.resID)
            else -> GlideApp.with(ctx).load(config.url.orEmpty())
        }

        if (config.isOpenTransition())
            glideRequest = glideRequest.transition(DrawableTransitionOptions.withCrossFade())

        if (config.getTransformation() != null) {
            glideRequest = glideRequest.transform(config.getTransformation()!!)
        } else {
            if (config.isCircle()) {
                glideRequest = glideRequest.transform(CircleCrop())
            }
        }


        if (config.placeholder != 0)//设置占位符
            glideRequest.placeholder(config.placeholder)

        if (config.errorPic != 0)//设置错误的图片
            glideRequest.error(config.errorPic)

        if (config.getFallback() != 0)//设置请求 url 为空图片
            glideRequest.fallback(config.getFallback())


        if (config.getRequestListener() != null) {
            glideRequest.listener(config.getRequestListener())
        }


        when (config.getCacheStrategy()) {
        //缓存策略
            0 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
            1 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE)
            2 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            3 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA)
            4 -> glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            else -> glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL)
        }

        if (config.getSkipMemoryCache()) {
            glideRequest.skipMemoryCache(true)
        }

        glideRequest
                .into(config.imageView)
    }

    override fun clear(ctx: Context?, config: GlideImageConfigImpl?) {
        if (ctx == null || config == null) {
            config?.getImageLoaderClearListener()?.onError("context has empty")
            return
        }

        val imageViews = config.getImageViews()

        val imageObservable = if (imageViews != null && imageViews.isNotEmpty()) {//取消在执行的任务并且释放资源
            Observable.create<Boolean> {
                try {
                    for (imageView in imageViews) {
                        GlideApp.get(ctx).requestManagerRetriever.get(ctx).clear(imageView)
                    }
                } catch (e: Exception) {
                    it.onError(e)
                }
                it.onNext(true)
                it.onComplete()
            }
                    .subscribeOn(AndroidSchedulers.mainThread())
        } else {
            Observable.just(true)
        }

        //必须背后线程
        val diskObservable = if (config.isClearDiskCache()) {
            Observable.create<Boolean> {
                try {
                    GlideApp.get(ctx).clearDiskCache()
                } catch (e: Exception) {
                    it.onError(e)
                }
                it.onNext(true)
                it.onComplete()
            }
                    .subscribeOn(Schedulers.io())
        } else {
            Observable.just(true)
        }


        //必须主线程
        val cacheObservable = if (config.isClearMemory()) {
            Observable.create<Boolean> {
                try {
                    GlideApp.get(ctx).clearMemory()
                } catch (e: Exception) {
                    it.onError(e)
                }
                it.onNext(true)
                it.onComplete()
            }
                    .subscribeOn(AndroidSchedulers.mainThread())
        } else {
            Observable.just(true)
        }

        Observable
                .zip(imageObservable, diskObservable, cacheObservable, Function3<Boolean, Boolean, Boolean, Boolean> { _, _, _ ->
                    true
                })
                .customSubscribeBy(onBaseError = {
                    config.getImageLoaderClearListener()?.onError(it.customMsg, it)
                }, onComplete = {
                    config.getImageLoaderClearListener()?.onSuccess()
                })

    }


    /**
     * 必须主线程
     */
    override fun getBitmap(ctx: Context?, config: GlideImageConfigImpl?) {
        if (ctx == null || config == null || (TextUtils.isEmpty(config.url) && config.resID == 0)) {
            config?.getImageLoaderListener()?.onError()
            return
        }

        var glideRequest = GlideApp
                .with(ctx)
                .asBitmap()


        //有资源图片载入资源图片 没有才载入网络图片
        glideRequest = when {
            config.resID != 0 -> glideRequest.load(config.resID)
            else -> glideRequest.load(config.url.orEmpty())
        }

        glideRequest
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        super.onLoadFailed(errorDrawable)
                        config.getImageLoaderListener()?.onError()
                    }

                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        config.getImageLoaderListener()?.onResourceReady(resource, transition)
                    }
                })

    }

    /**
     * 必须异步调用
     */
    override fun downloadFile(ctx: Context?, config: GlideImageConfigImpl?): File? {
        if (ctx == null || config == null || TextUtils.isEmpty(config.url)) {
            return null
        }

        return try {
            GlideApp
                    .with(ctx)
                    .load(config.url)
                    .downloadOnlyRequest
                    .submit()
                    .get()

        } catch (ex: Exception) {
            null
        }
    }


    override fun applyGlideOptions(context: Context, builder: GlideBuilder) {
    }
}