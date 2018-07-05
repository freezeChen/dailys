package com.frozen.daily.util



import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView
import com.frozen.daily.base.BaseNormalAdapter

interface AdapterHelp {
    companion object {
        const val ANIMATION_TYPE = BaseQuickAdapter.SCALEIN
    }

    var isLoading: Boolean

    abstract fun getDataInfo(lastData: Any?, page: Int)

    fun doOnRefresh() {
        getDataInfo(null, 1)
    }

    fun initLoadMoreAdapter(adapter: BaseNormalAdapter<*>, recyclerView: RecyclerView, needAnimation: Boolean = true) {
        adapter.setEnableLoadMore(true)
        if (needAnimation)
            adapter.openLoadAnimation(ANIMATION_TYPE)

        adapter.setLoadMoreView(SimpleLoadMoreView())
        adapter.bindToRecyclerView(recyclerView)
        adapter.setOnLoadMoreListener({ doOnLoadMore(adapter) }, recyclerView)
    }

    fun initEasyAdapter(adapter: BaseNormalAdapter<*>, recyclerView: RecyclerView, needAnimation: Boolean = true) {
        if (needAnimation)
            adapter.openLoadAnimation(ANIMATION_TYPE)
        adapter.bindToRecyclerView(recyclerView)
    }


    fun doOnLoadMore(adapter: BaseNormalAdapter<*>) {
        if (!isLoading) {
            isLoading = true
            checkLoadMore(adapter)
        }
    }

    fun checkLoadMore(adapter: BaseNormalAdapter<*>) {
        val last = adapter.data.last()
        if (adapter.data.size > 0) {
            getDataInfo(last, adapter.data.size / 10 + 1)
        }

    }


}