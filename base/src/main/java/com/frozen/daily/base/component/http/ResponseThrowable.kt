package com.frozen.daily.base.component.http

import com.frozen.daily.base.component.http.Errors

class ResponseThrowable(var code: Int = Errors.UNKNOWN, var customMessage: String = "", var throwable: Throwable) : Exception(throwable)

