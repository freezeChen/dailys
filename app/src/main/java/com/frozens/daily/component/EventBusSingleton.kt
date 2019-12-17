package com.frozens.daily.component

import org.greenrobot.eventbus.EventBus

class EventBusSingleton private constructor() {

    init {
        init()
    }

    companion object {
        fun getInstance(): EventBus = EventHolder.INSTANCE.getDefault()

    }

    fun getDefault(): EventBus = EventBus.getDefault()

    private object EventHolder {
        val INSTANCE = EventBusSingleton()
    }

    private fun init() {
//        EventBus.builder().build()
//        EventBus.builder().installDefaultEventBus()
//        EventBus.builder().addIndex(object ).installDefaultEventBus()
    }
}