package com.frozen.dailys.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseActivity
import com.frozen.dailys.ui.main.MainFragment
import com.frozen.dailys.ui.message.MessageDetailFragment

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

        if (findFragment(MainFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance())
        }

    }
}
