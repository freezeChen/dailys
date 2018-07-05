package com.frozen.daily.component.http

class Errors {

    companion object {
        /**
         * 约定异常
         */
        const val UNKNOWN = 1000
        const val PARSE_ERROR = 1001
        const val NETWORD_ERROR = 1002
        const val HTTP_ERROR = 1003
        const val SSL_ERROR = 1005
        const val TIME_OUT = 1006


        /**
         * HTTP异常
         */
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val REQUEST_TIMEOUT = 408
        const val INTERNAL_SERVER_ERROR = 500
        const val BAD_GATEWAY = 502
        const val SERVICE_UNAVAILABLE = 503
        const val GATEWAY_TIMEOUT = 504
    }

}