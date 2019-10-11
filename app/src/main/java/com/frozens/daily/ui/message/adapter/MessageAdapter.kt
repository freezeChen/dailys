package com.frozens.daily.ui.message.adapter

import com.frozens.daily.R
import com.frozens.daily.base.BaseNormalAdapter
import com.frozens.daily.base.MyBaseViewHolder
import com.frozens.daily.entity.Message


class MessageAdapter : BaseNormalAdapter<Message>(R.layout.message_list_item) {
    override fun convert(helper: MyBaseViewHolder, item: Message) {
        helper.apply {
            setText(R.id.title, item.title)
            setText(R.id.message, item.msg)
            setText(R.id.time, item.time)
            setText(R.id.count, item.count)
        }

    }
}