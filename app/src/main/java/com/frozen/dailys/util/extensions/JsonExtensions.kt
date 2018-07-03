package com.frozen.dailys.util.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by csc on 2018/5/8.
 */

fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.ParseJson(): T {
    return Gson().fromJson(this, object : TypeToken<T>() {}.type)
}

//inline fun <reified T> String.ParseListJson(): T {
//
//}
