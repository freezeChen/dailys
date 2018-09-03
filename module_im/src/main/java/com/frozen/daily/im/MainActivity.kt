package com.frozen.daily.im


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.frozen.daily.base.component.im.IMManager
import com.frozen.imsdk.listener.ConnectListener
import com.frozen.imsdk.manage.IMManage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.im_activity_main)

        IMManage.getInstance().setOnConnectListener(object : ConnectListener {
            override fun onFailed(msg: String?) {


            }

            override fun onSuccess() {

            }



            override fun onReconnect() {
            }
        })


        IMManage.getInstance().conversation
    }
}
