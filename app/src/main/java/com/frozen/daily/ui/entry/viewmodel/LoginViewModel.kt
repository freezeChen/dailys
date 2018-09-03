package com.frozen.daily.ui.entry.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.frozen.daily.base.base.BaseViewModel
import com.frozen.daily.model.User

class LoginViewModel(application: Application) : BaseViewModel(application) {
//    lateinit var repository: EntryRepository

    val user by lazy {
        MutableLiveData<User>()

    }

    val name = "1"

    fun start() {
//        repository = EntryRepository()
    }

    /*  fun login(account: String, psw: String): Disposable {
          return Observable.create<Boolean> {
              if (account.isEmpty() || psw.isEmpty()) {
                  it.onError(Throwable("账号密码不能为空"))
              }
              it.onNext(true)
              it.onComplete()
          }
                  .flatMap {
  //                    repository.login(account, psw)
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
      }*/
}