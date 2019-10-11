package com.frozens.daily.component

import androidx.databinding.DataBindingUtil
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.frozens.daily.R
import com.frozens.daily.databinding.EmptyViewBinding
import com.frozens.daily.widget.LoadView


interface AdapterLoadHelp {
    var refreshIsLoading: Boolean

    fun getAutoSize(): Int = Constants.AUTO_SIZE

    fun getPageSize(): Int = Constants.PAGE_SIZE


    fun isHasLoading(): Boolean = refreshIsLoading


    fun getDataInfo(page: Int, lastData: Any?) {
        setRefreshComplete()
    }


    fun doOnRefresh() {
        if (!refreshIsLoading) {
            refreshIsLoading = true
            refreshAfterCheck()
        }
    }

    fun refreshAfterCheck() {
        getDataInfo(1, null)
    }

    fun doOnLoadMore(adapter: BaseQuickAdapter<*, *>) {
        if (!refreshIsLoading) {
            refreshIsLoading = true
            loadMoreAfterCheck(adapter)
        }
    }

    fun setRefreshComplete() {
        this.refreshIsLoading = false
    }

    fun loadMoreAfterCheck(adapter: BaseQuickAdapter<*, *>) {
        getDataInfo(getPage(adapter), adapter.data.lastOrNull())
    }


    fun getPage(adapter: BaseQuickAdapter<*, *>): Int {
        return countPage(adapter.data.size)
    }

    fun countPage(dataSize: Int): Int {
        return dataSize / getPageSize() + 1
    }

    fun getLoadView(): LoadMoreView = LoadView()

    fun disableLoadMoreIfNotFullPage(): Boolean = false

    fun getAnimationType(): Int {
        return BaseQuickAdapter.ALPHAIN
    }

    fun initLoadAdapter(
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        adapter: BaseQuickAdapter<*, *>,
        @StringRes emptyRes: Int = R.string.txt_no_data, @DrawableRes emptyIconRes: Int? = R.drawable.ic_empty_logo,
        needEmptyView: Boolean = true
    ) {
        adapter.setLoadMoreView(getLoadView())
        adapter.openLoadAnimation(getAnimationType())
        adapter.setPreLoadNumber(getAutoSize())
        adapter.bindToRecyclerView(recyclerView)
        adapter.setOnLoadMoreListener({ doOnLoadMore(adapter) }, recyclerView)
        if (needEmptyView)
            setEmptyView(recyclerView, adapter, emptyRes, emptyIconRes)
        //禁止没满屏幕还进行加载
        if (disableLoadMoreIfNotFullPage()) {
            adapter.disableLoadMoreIfNotFullPage()
        }
    }

    fun initUpFetchAdapter(
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        adapter: BaseQuickAdapter<*, *>,
        @StringRes emptyRes: Int = R.string.txt_no_data, @DrawableRes emptyIconRes: Int? = null,
        needEmpty: Boolean = false
    ) {
        adapter.bindToRecyclerView(recyclerView)
        if (needEmpty) {
            setEmptyView(recyclerView, adapter, emptyRes)
        }
        adapter.setStartUpFetchPosition(getAutoSize())
        adapter.setUpFetchListener {
            doOnLoadMore(adapter)
        }
    }

    fun bindRecycler(
        recyclerView: RecyclerView,
        adapter: BaseQuickAdapter<*, *>,
        @StringRes emptyRes: Int = R.string.txt_no_data, @DrawableRes emptyIconRes: Int? = R.drawable.ic_empty_logo,
        needAnimate: Boolean = true,
        needEmpty: Boolean = true
    ) {
        adapter.bindToRecyclerView(recyclerView)
        if (needAnimate)
            adapter.openLoadAnimation(getAnimationType())
        if (needEmpty)
            setEmptyView(recyclerView, adapter, emptyRes, emptyIconRes)
    }

    fun getDataSize(adapter: BaseQuickAdapter<*, *>): Int = adapter.data.size

    fun setEmptyView(
        recyclerView: androidx.recyclerview.widget.RecyclerView, adapter: BaseQuickAdapter<*, *>,
        @StringRes emptyRes: Int, @DrawableRes emptyIconRes: Int? = null
    ) {
        val context = recyclerView.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.empty_view, recyclerView, false)
        val emptyBinding = DataBindingUtil.bind<EmptyViewBinding>(view)
        emptyBinding?.emptyTextView?.setText(emptyRes)
        if (emptyIconRes == null) {
            emptyBinding?.emptyImageView?.visibility = View.GONE
        } else {
            emptyBinding?.emptyImageView?.setImageResource(emptyIconRes)
        }
        adapter.emptyView = view
    }


    fun stopRefresh(layout: SwipeRefreshLayout) {
        layout.isRefreshing=false
    }

}
