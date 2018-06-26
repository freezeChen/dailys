package com.frozen.dailys.component

import com.frozen.dailys.component.http.RetrofitSingleton

class AppData {

    companion object {
        fun getArticleList(page: Int) =
                RetrofitSingleton
                        .getInstance()
                        .getApiService()
                        .getMainList(page)


    }
}