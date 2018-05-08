package com.frozen.dailys.model

import com.frozen.dailys.component.http.Errors

class ResponseThrowable(var code: Int = Errors.UNKNOWN, var customMessage: String = "", var throwable: Throwable) : Exception(throwable)

