package com.frozen.dailys.component.http

import android.net.ParseException
import com.bumptech.glide.load.HttpException
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseApplication
import com.frozen.dailys.model.ResponseThrowable
import com.google.gson.JsonParseException
import org.json.JSONException
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
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.network_error)
                    }
                    is ServerException -> {
                        ex.code = e.code
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.unknow_error)

                    }
                    is JsonParseException, is JSONException, is ParseException -> {
                        ex.code = Errors.PARSE_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.parse_error)
                        com.orhanobut.logger.Logger.e(e, ex.customMessage)
                    }
                    is ConnectException -> {
                        ex.code = Errors.NETWORD_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.connect_error)
                    }

                    is SSLHandshakeException -> {
                        ex.code = Errors.SSL_ERROR
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.ssl_error)

                    }
                    is SocketTimeoutException -> {
                        ex.code = Errors.TIME_OUT
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.timeout_error)
                    }
                    is ResponseThrowable -> {
                    }
                    else -> {
                        ex.code = Errors.UNKNOWN
                        ex.customMessage = BaseApplication.mBaseApplicationContext.getString(R.string.unknow_error)
                    }

                }
                ex
            }
        }

    }
}