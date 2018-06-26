package com.frozen.dailys.component

import com.frozen.dailys.model.Article
import com.frozen.dailys.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {


    @GET("article/list/{page}/json")
    fun getMainList(@Path("page") page: Int): Observable<Article>
}