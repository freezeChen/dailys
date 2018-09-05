package com.frozen.daily.im


import android.arch.lifecycle.Observer
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.IBinder
import com.frozen.daily.im.databinding.ImActivityMainBinding
import com.frozen.daily.im.service.ImService
import com.frozen.daily.im.ui.message.MessageListFragment
import com.frozen.imsdk.listener.ConnectListener
import com.frozen.imsdk.manage.IMManage
import com.orhanobut.logger.Logger
import me.yokeyword.fragmentation.SupportActivity
import org.jetbrains.anko.toast

class MainActivity : SupportActivity() {
    private lateinit var mBinding: ImActivityMainBinding
    private var mBinder: ImService.MBinder? = null

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Logger.i("service disconnect")
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Logger.i("service connect")
            mBinder = p1 as? ImService.MBinder
//            mBinder?.viewModel?.start()

//            IMManage.getInstance().login(110)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.im_activity_main)

        initim()
/*
        val intent = Intent(this, ImService::class.java)
        startService(intent)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)



        supportFragmentManager.beginTransaction()
                .add(R.id.frame_container, MessageListFragment.newInstance())
                .commit()

        mBinder?.apply {
            viewModel.message.observe(this@MainActivity, Observer {
                toast(it.orEmpty())
            })
            viewModel.online.observe(this@MainActivity, Observer {
                it?.run {
                    if (this) {

                        IMManage.getInstance().login(1)

                        toast("在线")
                    } else {
                        toast("离线")
                    }
                }
            })
        }
*/


    }

    private fun initim() {
        IMManage.getInstance().setOnConnectListener(object : ConnectListener {
            override fun onSuccess() {
                Logger.e("onsuccess")


                IMManage.getInstance().login(123)
            }

            override fun onFailed(msg: String?) {
                Logger.e("onsuccess")
            }

            override fun onReconnect() {
                Logger.e("onsuccess")
            }
        })

        IMManage.getInstance().init()
    }

    override fun onDestroy() {
//        unbindService(mServiceConnection)
//        mBinder == null
        super.onDestroy()

    }
}
