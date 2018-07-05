//package com.frozen.daily.component
//
//import com.frozen.daily.model.Article
//import com.frozen.daily.model.Response
//import com.frozen.daily.model.User
//import io.reactivex.Observable
//import retrofit2.http.*
//
//interface APIService {
//
//
//    @GET("article/list/{page}/json")
//    fun getMainList(@Path("page") page: Int): Observable<Article>
//
//
//    @FormUrlEncoded
//    @POST("account/login")
//    fun login(@Field("FAccount") FAccount: String, @Field("FPassword") FPassword: String): Observable<Response<User>>
//}