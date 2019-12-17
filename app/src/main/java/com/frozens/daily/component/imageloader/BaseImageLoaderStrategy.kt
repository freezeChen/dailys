package com.frozens.daily.component.imageloader

import android.content.Context
import java.io.File


/**
 * Created by xiang23 on 2018/3/22.
 */
interface BaseImageLoaderStrategy<in T : ImageConfig> {
    /**
     * 加载图片
     *
     * @param ctx
     * @param config
     */
    fun loadImage(ctx: Context?, config: T?)

    /**
     * 停止加载
     *
     * @param ctx
     * @param config
     */
    fun clear(ctx: Context?, config: T?)


    fun getBitmap(ctx: Context?, config: T?)


    fun downloadFile(ctx: Context?, config: T?): File?
}
