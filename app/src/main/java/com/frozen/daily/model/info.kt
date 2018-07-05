package com.frozen.daily.model

/**
 * Created by csc on 2018/5/2.
 */

data class Info(
        var FUserID: String = "",
        var FInfo: FInfo? = null,
        var FCode: Int = 0
)

data class FInfo(
        var FTOID: String = "",
        var FType: Int = 0,
        var FContent: String = "",
        var FTime: String = ""
)