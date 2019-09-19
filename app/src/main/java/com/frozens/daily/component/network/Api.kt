package com.frozens.daily.component.network

import com.frozens.daily.entity.User
import com.frozens.daily.entity.Response
import com.frozens.daily.utils.RxUtils
import io.reactivex.Observable


class Api {

    companion object {

        fun login(account: String, password: String): Observable<Response<User>> {
            return RetrofitSingleton.getInstance()
                .getApiService()
                .login(account, password)
                .compose(RxUtils.errorTransformer())
                .onErrorResumeNext(HttpResponseFunc())
        }
    }
}