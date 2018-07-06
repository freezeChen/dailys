package com.frozen.daily.base.util.extensions

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


/**
 * Created by csc on 2018/5/8.
 */

fun String.md5(isUp: Boolean = false): String {
    try {
        var result = ""
        MessageDigest.getInstance("MD5").apply {
            val bytes = digest(this@md5.toByteArray())
            for (byte in bytes) {
                var temp = Integer.toHexString(byte.toInt() and 0xff)
                if (temp.length == 1) temp = "0$temp"
                result += temp
            }
            return if (isUp) result.toUpperCase() else result
        }
    } catch (e: NoSuchAlgorithmException) {
        e.printStackTrace()
    }
    return ""
}