package com.frozens.daily.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.frozens.daily.component.AdapterLoadHelp


import com.frozens.daily.R
import com.frozens.daily.base.BaseFragment
import com.frozens.daily.component.EventBusSingleton
import com.frozens.daily.component.imService.event.MsgEvent
import com.frozens.daily.databinding.FragmentMessageBinding
import com.frozens.daily.entity.Message
import com.frozens.daily.ui.message.adapter.MessageAdapter
import com.frozens.daily.utils.RxUtils
import com.frozens.daily.utils.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MessageFragment : BaseFragment(), AdapterLoadHelp, SwipeRefreshLayout.OnRefreshListener {


    override var refreshIsLoading = false

    lateinit var mBinding: FragmentMessageBinding

    private val mAdapter by lazy {
        MessageAdapter()
    }


    companion object {
        fun NewInstance(): BaseFragment {
            val fragment = MessageFragment()

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_message, container, false)
        EventBusSingleton.getInstance().register(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()


    }

    private fun initView() {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        initLoadAdapter(mBinding.recyclerView, mAdapter)

        mBinding.refreshLayout.setOnRefreshListener(this)

    }


    override fun getDataInfo(page: Int, lastData: Any?) {

        Observable.just(
            arrayListOf(
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+"),
                Message(12, "小明", "hello", "15:32", "99+")
            )
        )
            .compose(RxUtils.progressTransformer())
            .customSubscribeBy(
                onComplete = {
                    setRefreshComplete()
                    stopRefresh(mBinding.refreshLayout)
                },
                onNext = {
                    mAdapter.addData(it)
                    mAdapter.loadMoreComplete()
                },
                onSubscribe = {
                    addDispose(it)
                }
            )


    }


    override fun onRefresh() {
        doOnRefresh()
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(event: MsgEvent) {
        Logger.i(event.protocol.msg.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBusSingleton.getInstance().unregister(this)
    }

}