package com.frozen.dailys.components.http

class ServerException(var code:Int,var customMessage:String=""):RuntimeException()

