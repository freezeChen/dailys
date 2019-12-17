package com.frozens.daily.ui.main

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.databinding.DataBindingUtil
import com.frozens.daily.R
import com.frozens.daily.base.BaseActivity
import com.frozens.daily.component.EventBusSingleton
import com.frozens.daily.component.imService.ImService
import com.frozens.daily.component.imService.event.ConnectStateEvent
import com.frozens.daily.component.imService.event.MsgEvent
import com.frozens.daily.component.netty.NettyClient
import com.frozens.daily.component.websocket.WebsocketClient
import com.frozens.daily.databinding.ActivityMainBinding
import com.frozens.daily.ui.link.LinkFragment
import com.frozens.daily.ui.main.adapter.MainAdapter
import com.frozens.daily.ui.message.MessageFragment
import com.orhanobut.logger.Logger
import com.qmuiteam.qmui.widget.QMUITabSegment
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

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
        EventBusSingleton.getInstance().register(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()

        initService()
    }

    private fun initService() {
        val intent = Intent(this, ImService::class.java)

        startService(intent)


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


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(event: ConnectStateEvent) {



    }


    override fun onDestroy() {
        super.onDestroy()
        EventBusSingleton.getInstance().unregister(this)
    }

}



