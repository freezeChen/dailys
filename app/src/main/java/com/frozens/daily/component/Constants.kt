package com.frozens.daily.component

import android.os.Environment


/**
 * Created by chx on 2017/9/25.
 */
class Constants {
    companion object {
        const val PAGE_SIZE = 10
        const val AUTO_SIZE = 2

        const val PREF_USER = "pref_user"
        const val PREF_TOKEN = "pref_token"
        const val PREF_IP = "pref_ip"
        const val PREF_PORT = "pref_port"
        const val PREF_USE_DEFAULT_URL = "pref_use_default_url"

        const val IS_FIRST = "is_first"


        const val REQUEST_CROP_CODE = 5000

        const val DEFAULT_COUNT_DOWN = 120

        const val DEFAULT_SPLIT_STR = "*"

        const val NEED_NOTIFY = "need_notify"

        const val SHOW_NOTIFY = "show_notify"


        val DEFAULT_DOWNLOAD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath


        const val WEB_SITE = "https://www.24on.cn/"


    }

}