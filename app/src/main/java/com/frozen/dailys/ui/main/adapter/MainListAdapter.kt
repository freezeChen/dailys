package com.frozen.dailys.ui.main.adapter

import com.frozen.dailys.R
import com.frozen.dailys.base.BaseNormalAdapter
import com.frozen.dailys.base.MyBaseViewHolder
import com.frozen.dailys.model.ArticleList

class MainListAdapter : BaseNormalAdapter<ArticleList>(R.layout.item_main) {
    override fun convert(helper: MyBaseViewHolder, item: ArticleList) {
        with(helper) {
            setText(R.id.tv_title, item.title)
        }
    }
}