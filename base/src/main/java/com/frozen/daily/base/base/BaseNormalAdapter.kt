package com.frozen.daily.base.base

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter


/**
 * Created by csc on 2018/5/8.
 */
abstract class BaseNormalAdapter<T> : BaseQuickAdapter<T, MyBaseViewHolder> {

    constructor(layoutResId: Int, data: List<T>?) : super(layoutResId, data)
    constructor(data: List<T>?) : super(data)
    constructor(layoutResId: Int) : super(layoutResId)


    override fun createBaseViewHolder(view: View): MyBaseViewHolder {
        return MyBaseViewHolder(view)
    }
}
