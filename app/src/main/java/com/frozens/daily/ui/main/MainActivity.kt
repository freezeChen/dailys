package com.frozens.daily.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.frozens.daily.R
import com.frozens.daily.base.BaseActivity
import com.frozens.daily.databinding.ActivityMainBinding
import com.frozens.daily.ui.link.LinkFragment
import com.frozens.daily.ui.main.adapter.MainAdapter
import com.frozens.daily.ui.message.MessageFragment
import com.qmuiteam.qmui.widget.QMUITabSegment

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding

    private val mFragments by lazy {
        arrayOf(
            MessageFragment.NewInstance(),
            LinkFragment.NewInstance()
        )
    }

    private val mViewPagerAdapter by lazy {
        MainAdapter(supportFragmentManager, mFragments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initView()
    }

    private fun initView() {

    mBinding.viewpager.adapter = mViewPagerAdapter

        mBinding.tabs.apply {
            addTab(QMUITabSegment.Tab("消息"))
            addTab(QMUITabSegment.Tab("联系人"))
            setDefaultSelectedColor(0xFF0f7ded.toInt())
            setDefaultNormalColor(0xFF353535.toInt())
            setupWithViewPager(mBinding.viewpager)
        }

//        addTab(QMUITabSegment.Tab("消息"))
//            .addTab(QMUITabSegment.Tab("联系人"))
//            .addTab(QMUITabSegment.Tab("我的"))



        mBinding.tabs.selectTab(0)
    mBinding.tabs.notifyDataChanged()
    }



}
