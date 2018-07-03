package com.frozen.dailys.component

import com.bumptech.glide.request.ErrorRequestCoordinator
import com.frozen.dailys.component.http.RetrofitSingleton
import com.frozen.dailys.util.ResponseFunction
import com.frozen.dailys.util.RxUtils

class AppData {

    companion object {
        fun getArticleList(page: Int) =
                RetrofitSingleton
                        .getInstance()
                        .getApiService()
                        .getMainList(page)

        fun login(FAccount: String, FPassword: String) =
                RetrofitSingleton
                        .getInstance()
                        .getApiService()
                        .login(FAccount, FPassword)
                        .compose(RxUtils.responseTransformer())
                        .onErrorResumeNext(ResponseFunction())


    }
}