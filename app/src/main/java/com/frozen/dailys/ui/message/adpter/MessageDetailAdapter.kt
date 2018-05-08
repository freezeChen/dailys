package com.frozen.dailys.ui.message.adpter

import com.chad.library.adapter.base.util.MultiTypeDelegate
import com.frozen.dailys.R
import com.frozen.dailys.base.BaseNormalAdapter
import com.frozen.dailys.base.MyBaseViewHolder
import com.frozen.dailys.component.DataLab
import com.frozen.dailys.model.Info


class MessageDetailAdapter() : BaseNormalAdapter<Info>(null) {

    companion object {
        const val TYPE_HELPER = 0x00
        const val TYPE_MESSAGE = 0x01
    }

    init {
        multiTypeDelegate = object : MultiTypeDelegate<Info>() {
            override fun getItemType(t: Info?): Int {
                return TYPE_MESSAGE
            }
        }

        multiTypeDelegate.registerItemType(TYPE_HELPER, R.layout.item_message_detail_helper)
        multiTypeDelegate.registerItemType(TYPE_MESSAGE, R.layout.item_message_detail)
    }


    override fun convert(helper: MyBaseViewHolder, item: Info?) {
        if (item?.FUserID == DataLab.getInstance().user?.FUserID) {
            helper.setGone(R.id.layout_right, true)
                    .setGone(R.id.layout_left, false)
                    .setText(R.id.tv_name_right, item?.FUserID)
                    .setText(R.id.tv_content_right, item?.FInfo?.FContent)
        } else {
            helper.setGone(R.id.layout_right, false)
                    .setGone(R.id.layout_left, true)
                    .setText(R.id.tv_name, item?.FUserID)
                    .setText(R.id.tv_content, item?.FInfo?.FContent)
        }
    }
}
