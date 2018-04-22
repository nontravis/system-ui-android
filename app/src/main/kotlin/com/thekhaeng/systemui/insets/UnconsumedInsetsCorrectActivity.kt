package com.thekhaeng.systemui.insets

import com.thekhaeng.systemui.base.CommonInsetsActivity

class UnconsumedInsetsCorrectActivity : CommonInsetsActivity() {

    override
    fun correct(): Boolean {
        return true
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