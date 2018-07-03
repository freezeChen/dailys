package com.frozen.dailys.model

import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("Fid") val fid: String = "",
        @SerializedName("Fname") val fname: String = "",
        @SerializedName("Fheadicon") val fheadicon: String = "",
        @SerializedName("Faccount") val faccount: String = "",
        @SerializedName("Fpassword") val fpassword: String = "",
        @SerializedName("Fsex") val fsex: Int = 0,
        @SerializedName("Fcreatetime") val fcreatetime: String = "",
        @SerializedName("Token") val token: String = ""
)