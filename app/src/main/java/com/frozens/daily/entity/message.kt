package com.frozens.daily.entity

data class Message(
    val id: Long,
    val title: String,
    val msg: String,
    val time: String,
    var count: String

)