package com.frozens.daily.ui.entry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.frozens.daily.R
import com.frozens.daily.databinding.ActivityLoginBinding
import com.frozens.daily.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setContentView(R.layout.activity_login)

        mBinding.btnLogin.setOnClickListener {
            login()
        }
    }


    fun login() {

        val account = mBinding.editAccount.text
        val psw = mBinding.editPsw.text

        if (account.isEmpty() or psw.isEmpty()) {
            toast("账号或密码不能为空")
            return
        }

    }
}
