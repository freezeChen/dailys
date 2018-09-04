package com.frozen.daily.im


import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.frozen.daily.im.service.ImService
import com.frozen.imsdk.listener.ConnectListener
import com.frozen.imsdk.manage.IMManage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.im_activity_main)

        var intent = Intent(this, ImService::class.java)

        bindService(intent, object : ServiceConnection {
            override fun onServiceDisconnected(p0: ComponentName?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }, Context.BIND_AUTO_CREATE);

    }
}
