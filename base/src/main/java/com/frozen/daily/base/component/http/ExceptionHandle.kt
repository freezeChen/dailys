package com.frozen.daily.base.component.http

import android.net.ParseException


import com.frozen.daily.base.base.BaseApplication
import com.frozen.daily.base.R
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

class ExceptionHandle {
    companion object {
        fun handleException(e: Throwable): ResponseThrowable {
            val ex = ResponseThrowable(throwable = e)
            return with(ex) {
                when (e) {
                    is HttpException -> {
                        ex.code = Errors.HTTP_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_network_error)
                    }
                    is ServerException -> {
                        ex.code = e.code
                        ex.customMessage = e.customMessage

                    }
                    is JsonParseException, is JSONException, is ParseException -> {
                        ex.code = Errors.PARSE_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_parse_error)
                        com.orhanobut.logger.Logger.e(e, ex.customMessage)
                    }
                    is ConnectException -> {
                        ex.code = Errors.NETWORD_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_connect_error)
                    }

                    is SSLHandshakeException -> {
                        ex.code = Errors.SSL_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_ssl_error)

                    }
                    is SocketTimeoutException -> {
                        ex.code = Errors.TIME_OUT
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_timeout_error)
                    }
                    is ResponseThrowable -> {
                    }
                    else -> {
                        ex.code = Errors.UNKNOWN
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.base_unknow_error)
                    }

                }
                ex
            }
        }

    }
}