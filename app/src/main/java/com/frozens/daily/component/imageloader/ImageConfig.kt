package com.frozens.daily.component.imageloader

import android.widget.ImageView

/**
 * Created by xiang23 on 2018/3/22.
 * 这里是图片加载配置信息的基类,定义一些所有图片加载框架都可以用的通用参数
 */
open class ImageConfig(
        val url: String?,
        val resID:Int,//资源id
        val imageView: ImageView?,
        val placeholder: Int,//占位符
        val errorPic: Int//错误占位符
)