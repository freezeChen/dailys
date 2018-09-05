package com.frozen.daily.im.ui.message

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.adapters.ToolbarBindingAdapter
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frozen.daily.base.base.BaseApplication
import com.frozen.daily.base.base.BaseFragment
import com.frozen.daily.base.component.ViewModelFactory
import com.frozen.daily.base.util.AdapterHelp
import com.frozen.daily.base.util.extensions.getProgressDialog
import com.frozen.daily.im.R
import com.frozen.daily.im.databinding.ImFragmentMessageListBinding
import com.frozen.daily.im.ui.message.apdater.MessageListAdapter
import com.frozen.daily.im.ui.message.viewmodel.MessageListViewModel
import org.jetbrains.anko.toast

class MessageListFragment : BaseFragment(), AdapterHelp, SwipeRefreshLayout.OnRefreshListener {
    override var isLoading: Boolean = false
    private lateinit var mBinding: ImFragmentMessageListBinding

    private val mAdapter by lazy {
        MessageListAdapter()
    }

    private val mViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(_mActivity.application)).get(MessageListViewModel::class.java)
    }

    private val mProgress by lazy {
        _mActivity.getProgressDialog()
    }

    companion object {
        fun newInstance(): BaseFragment {
            return MessageListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.im_fragment_message_list, container, false)
        return mBinding.root
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        initLoadMoreAdapter(mAdapter, mBinding.recyclerView)
        setupViewModel()

    }

    private fun setupViewModel() {
        mViewModel.showProgress.observe(this, Observer {
            it?.let {
                if (it) {
                    mProgress.show()
                } else {
                    mProgress.dismiss()
                }
            }
        })

        mViewModel.loadEnd.observe(this, Observer {
            it?.apply {
                if (this) {
                    mAdapter.loadMoreEnd()
                } else {
                    mAdapter.loadMoreComplete()
                }
            }
        })

        mViewModel.message.observe(this, Observer {
            context?.toast(it.orEmpty())
        })
    }

    override fun getDataInfo(lastData: Any?, page: Int) {
        mViewModel.getData(lastData, page)
    }

    override fun onRefresh() {
        getDataInfo(null, 1)
    }
}