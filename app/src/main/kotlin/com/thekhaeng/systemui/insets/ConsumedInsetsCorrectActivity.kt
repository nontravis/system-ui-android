package com.thekhaeng.systemui.insets

import com.thekhaeng.systemui.base.CommonInsetsActivity

class ConsumedInsetsCorrectActivity : CommonInsetsActivity() {

    override
    fun correct(): Boolean {
        return true
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