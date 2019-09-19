package com.frozens.daily.component.network

import java.lang.RuntimeException

data class ResponseThrowable(
    var code: Int = Errors.UnKnow,
    var customMsg: String = "",
    var throwable: Throwable = RuntimeException()
) : Exception(throwable)