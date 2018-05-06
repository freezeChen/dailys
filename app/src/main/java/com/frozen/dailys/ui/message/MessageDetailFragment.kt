package com.frozen.dailys.ui.message

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseFragment
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButtonDrawable
import kotlinx.android.synthetic.main.fragment_message_detail.*

class MessageDetailFragment : BaseFragment() {

    companion object {
        fun newInstance(): BaseFragment {
            val fragment = MessageDetailFragment()
            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_message_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edt_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        btn_send.setOnClickListener {
            if (edt_input.text.isEmpty()) {
                QMUIDialog.EditTextDialogBuilder(context)
                        .setDefaultText("请输入用户名称")
                        .create()
            } else {

            }

        }

    }

}