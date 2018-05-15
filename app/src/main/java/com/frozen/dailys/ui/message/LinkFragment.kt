package com.frozen.dailys.ui.message

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseFragment
import com.frozen.dailys.component.im.IMManager
import com.frozen.dailys.component.service.IMService
import com.frozen.dailys.databinding.FragmentLinkBinding

/**
 * Created by csc on 2018/5/15.
 */
class LinkFragment : BaseFragment() {

    private lateinit var mBinding: FragmentLinkBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_link, container, false)
        return mBinding.root
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        super.onEnterAnimationEnd(savedInstanceState)


        mBinding.btnLink.setOnClickListener {

        }

        mBinding.btnEnter.setOnClickListener {
            start(MessageDetailFragment.newInstance())
        }

    }

}