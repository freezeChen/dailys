package com.frozen.dailys.ui.entry

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseActivity
import com.frozen.dailys.component.DataLab
import com.frozen.dailys.component.ViewModelFactory
import com.frozen.dailys.component.im.IMManager
import com.frozen.dailys.component.service.IMService
import com.frozen.dailys.databinding.ActivityLoginBinding
import com.frozen.dailys.ui.MainActivity
import com.frozen.dailys.ui.entry.viewmodel.LoginViewModel
import com.frozen.dailys.util.RxManage
import com.frozen.dailys.util.extensions.getProgressDialog
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.toast


class LoginActivity : BaseActivity(), RxManage {
    override var mCompositeDisposable: CompositeDisposable? = null
    private lateinit var mBinding: ActivityLoginBinding

    private val mViewModel by lazy {
        ViewModelFactory.getInstance(application).create(LoginViewModel::class.java)
    }

    private val progress by lazy {
        getProgressDialog()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initView()
    }

    private fun initView() {
        mBinding.emailSignInButton.setOnClickListener {
            addDisposable(mViewModel.login(mBinding.account.text.toString(), mBinding.password.text.toString()))
        }
        setUpViewModel()
    }

    private fun setUpViewModel() {
        mViewModel.start()
        mViewModel.apply {
            user.observe(this@LoginActivity, Observer {
                DataLab.getInstance().user = it
                if (it != null) {

                    this@LoginActivity.startService(Intent(this@LoginActivity, IMService::class.java))

                    MainActivity.newIntent(this@LoginActivity)
                    finish()
                }
            })

            message.observe(this@LoginActivity, Observer {
                toast(it.orEmpty())
            })

            showProgress.observe(this@LoginActivity, Observer {
                it?.let {
                    if (it)
                        progress.show()
                    else
                        progress.dismiss()
                }
            })
        }
    }
}