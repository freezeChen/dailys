package com.frozens.daily.component.network

import com.frozens.daily.entity.User
import com.frozens.daily.entity.Response
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface ApiInterface {

    @FormUrlEncoded
    @POST("account/login")
    fun login(@Field("account") account: String, @Field("password") psw: String): Observable<Response<User>>

}