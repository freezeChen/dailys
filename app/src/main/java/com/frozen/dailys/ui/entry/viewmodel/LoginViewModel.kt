package com.frozen.dailys.ui.entry.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.frozen.dailys.base.BaseViewModel
import com.frozen.dailys.component.AppData
import com.frozen.dailys.model.User
import com.frozen.dailys.ui.entry.EntryRepository
import com.frozen.dailys.util.RxUtils
import com.frozen.dailys.util.extensions.customSubscribeBy
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class LoginViewModel(application: Application) : BaseViewModel(application) {
    lateinit var repository: EntryRepository

    val user by lazy {
        MutableLiveData<User>()
    }

    fun start() {
        repository = EntryRepository()
    }

    fun login(account: String, psw: String): Disposable {
        return Observable.create<Boolean> {
            if (account.isEmpty() || psw.isEmpty()) {
                it.onError(Throwable("账号密码不能为空"))
            }
            it.onNext(true)
            it.onComplete()
        }
                .flatMap {
                    repository.login(account, psw)
                }
                .compose(RxUtils.progressTransformer(showProgress))
                .customSubscribeBy(
                        onError = {
                            message.value = it.customMessage
                            Logger.e(it.message)
                        },
                        onNext = {
                            user.value = it.data
                        })
    }
}