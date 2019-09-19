package com.frozens.daily.component.network

import com.frozens.daily.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitSingleton private constructor() {

    init {
        init()
    }


    companion object {
        fun getInstance(): RetrofitSingleton {
            return Holder.Instance
        }

        private var retrofit: Retrofit? = null
        private var okHttpClient: OkHttpClient? = null
        private val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
        private var apiService: ApiInterface? = null

    }


    private fun init() {
        initOkHttp()
        initRetrofit()
        apiService = retrofit!!.create(ApiInterface::class.java)
    }

    private fun initOkHttp() {
        okHttpClient = OkHttpClient.Builder()
            .apply {
                if (BuildConfig.DEBUG_MODE) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }

            }
//            .addInterceptor(initTokenInterceptor())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient!!)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getApiService(): ApiInterface {
        return apiService!!
    }

    private object Holder {
        val Instance = RetrofitSingleton()
    }

}