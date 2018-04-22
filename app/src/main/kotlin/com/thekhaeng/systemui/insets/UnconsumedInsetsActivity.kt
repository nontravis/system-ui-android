package com.thekhaeng.systemui.insets

import com.thekhaeng.systemui.base.CommonInsetsActivity

class UnconsumedInsetsActivity : CommonInsetsActivity() {

    override
    fun correct(): Boolean {
        return false
    }


    override
    fun consumeInsets(): Boolean {
        return false
    }

    override
    fun requestFullscreenFlags(): Boolean {
        return true
    }
}