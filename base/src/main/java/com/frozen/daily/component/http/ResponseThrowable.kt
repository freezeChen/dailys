package com.frozen.daily.model

import com.frozen.daily.component.http.Errors

class ResponseThrowable(var code: Int = Errors.UNKNOWN, var customMessage: String = "", var throwable: Throwable) : Exception(throwable)

