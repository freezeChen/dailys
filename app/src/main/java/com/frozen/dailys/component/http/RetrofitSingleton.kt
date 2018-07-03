package com.frozen.dailys.component.http

import com.frozen.dailys.component.APIService
import com.frozen.dailys.component.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitSingleton {


    private var retrofit: Retrofit? = null
    private var apiService: APIService? = null
    private var client: OkHttpClient? = null

    companion object {
        fun getInstance() = Inner.INSTANCE
    }

    init {
        initClient()
        initRetrofit()
        initService()
    }

    object Inner {
        val INSTANCE = RetrofitSingleton()

    }

    fun getApiService() = apiService!!

    private fun initService() {
        apiService = retrofit!!.create(APIService::class.java)
    }

    private fun initClient() {
        client = OkHttpClient()
                .newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}