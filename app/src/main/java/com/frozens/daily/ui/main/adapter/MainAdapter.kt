package com.frozens.daily.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.frozens.daily.base.BaseFragment


class MainAdapter(fm:FragmentManager, val fragments:Array<BaseFragment>) :FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {

       return when (position) {
            0 -> {
                "消息"
            }
            else -> {
                "联系人"
            }
        }

    }

}