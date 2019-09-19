package com.frozens.daily.ui.entry

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.frozens.daily.R
import com.frozens.daily.base.BaseActivity
import com.frozens.daily.component.network.Api
import com.frozens.daily.databinding.ActivityLoginBinding
import com.frozens.daily.ui.MainActivity
import com.frozens.daily.utils.RxUtils
import com.frozens.daily.utils.extensions.customSubscribeBy
import com.frozens.daily.utils.extensions.toast

class LoginActivity : BaseActivity() {
    lateinit var mBinding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(R.layout.activity_login)

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }


    private fun login() {
        val account = mBinding.editAccount.text
        val psw = mBinding.editPsw.text

        if (account.isEmpty() or psw.isEmpty()) {
            toast("账号或密码不能为空")
            return
        }

        Api.login(account.toString(), psw.toString())
            .compose(RxUtils.progressTransformer())
            .customSubscribeBy(
                onComplete = {},
                onNext = {
                    //                    this.startActivity()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            )

    }
}
