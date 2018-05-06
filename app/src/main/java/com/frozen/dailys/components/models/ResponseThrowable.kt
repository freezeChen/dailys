package com.frozen.dailys.components.models

import com.frozen.dailys.components.http.Errors

class ResponseThrowable(var code: Int = Errors.UNKNOWN, var customMessage: String = "", var throwable: Throwable) : Exception(throwable)

