package com.frozens.daily.component.imageloader.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by chenhongxiang on 2017/11/14.
 */

@GlideModule
class GlideConfiguration : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean = false


    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }
}