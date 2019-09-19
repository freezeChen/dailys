package com.frozens.daily.component.network

import java.lang.Exception

data class ServerException(val code: Int, val customMsg: String?):Exception()