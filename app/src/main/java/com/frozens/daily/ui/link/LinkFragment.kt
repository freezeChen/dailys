package com.frozens.daily.ui.link

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.frozens.daily.R
import com.frozens.daily.base.BaseFragment
import com.frozens.daily.databinding.FragmentLinkBinding

class LinkFragment:BaseFragment(){

    lateinit var mBinding:FragmentLinkBinding

    companion object{
        fun NewInstance():BaseFragment{
            val fragment =LinkFragment()
            return fragment
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_link,container,false)
        return mBinding.root
    }

}