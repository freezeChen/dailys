package com.frozens.daily.component.network

import retrofit2.HttpException
import java.util.concurrent.ExecutorCompletionService

class ExceptionHandle {


    companion object {

        fun handleException(e: Throwable): ResponseThrowable {
            val ex = ResponseThrowable(throwable = e)

            when (e) {
                is HttpException -> {
                    ex.customMsg = "网络错误"
                }
                is ServerException -> {
                    ex.code = e.code
                    ex.customMsg = e.customMsg ?: "未知错误"
                }
                else -> {
                    ex.customMsg = "其他错误"
                }

            }


            return ex
        }
    }

}
