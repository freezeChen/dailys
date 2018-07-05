package com.frozen.daily.component.http

class ServerException(var code:Int,var customMessage:String=""):RuntimeException()

