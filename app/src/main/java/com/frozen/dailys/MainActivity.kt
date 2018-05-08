package com.frozen.dailys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.frozen.dailys.base.BaseActivity
import com.frozen.dailys.component.service.IMService
import com.frozen.dailys.ui.message.MessageDetailFragment
import org.jetbrains.anko.startService

class MainActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService<IMService>()

        if (findFragment(MessageDetailFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MessageDetailFragment.newInstance())
        }

    }
}
