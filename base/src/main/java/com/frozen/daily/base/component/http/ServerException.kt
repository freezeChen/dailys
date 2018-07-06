package com.frozen.daily.base.component.http

class ServerException(var code:Int,var customMessage:String=""):RuntimeException()

