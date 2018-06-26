package com.frozen.dailys.ui.main

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.frozen.dailys.R
import com.frozen.dailys.base.BaseFragment
import com.frozen.dailys.component.ViewModelFactory
import com.frozen.dailys.databinding.MainFragmentBinding
import com.frozen.dailys.databinding.MainFragmentBindingImpl
import com.frozen.dailys.ui.main.adapter.MainListAdapter
import com.frozen.dailys.util.AdapterHelp
import org.jetbrains.anko.toast

class MainFragment : BaseFragment(), AdapterHelp {

    override var isLoading: Boolean = false


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mBinding: MainFragmentBinding

    private val mAdapter by lazy {
        MainListAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelFactory.getInstance(application = _mActivity.application).create(MainViewModel::class.java)

        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }

        initLoadMoreAdapter(mAdapter, mBinding.recyclerView)

        doOnRefresh()
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)

        viewModel.showProgress.observe(this, Observer {
            when (it) {
                true -> {
                    progress.show()
                }
                else -> {
                    progress.dismiss()
                }
            }
        })



        viewModel.allData.observe(this, Observer {
            mAdapter.setNewData(it?.datas)
        })

        viewModel.message.observe(this, Observer {
            context?.toast(it.orEmpty())
        })
    }

    override fun getDataInfo(lastData: Any?, page: Int) {
        viewModel.getData(lastData, page)
    }


}
