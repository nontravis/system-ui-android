package com.thekhaeng.systemui.insets

import com.thekhaeng.systemui.base.CommonInsetsActivity

class ConsumedInsetsActivity : CommonInsetsActivity() {

    override
    fun correct(): Boolean {
        return false
    }


    override
    fun consumeInsets(): Boolean {
        return true
    }

    override
    fun requestFullscreenFlags(): Boolean {
        return true
    }

}