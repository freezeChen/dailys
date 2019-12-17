package com.frozens.daily.component.imageloader.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Glide



/**
 * Created by xiang23 on 2018/3/22.
 *  * 如果你想具有配置 @{@link Glide} 的权利,则需要让 {@link BaseImageLoaderStrategy}
 * 的实现类也必须实现 {@link GlideAppliesOptions}
 *
 */

interface GlideAppliesOptions {

    /**
     * 配置 @[Glide] 的自定义参数,此方法在 @[Glide] 初始化时执行(@[Glide] 在第一次被调用时初始化),只会执行一次
     *
     * @param context
     * @param builder [GlideBuilder] 此类被用来创建 Glide
     */
    fun applyGlideOptions(context: Context, builder: GlideBuilder)
}