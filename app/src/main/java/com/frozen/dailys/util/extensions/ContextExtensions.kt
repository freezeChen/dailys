package com.frozen.dailys.util.extensions

import android.app.Dialog
import android.content.Context
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog.Builder.ICON_TYPE_NOTHING

fun Context.getProgressDialog(): Dialog {
    return QMUITipDialog.Builder(this)
            .setIconType(ICON_TYPE_NOTHING)
            .create()
}