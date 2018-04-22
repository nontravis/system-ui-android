package com.thekhaeng.systemui.base

import android.support.annotation.Px
import android.view.View
import android.view.ViewGroup


/**
 * Created by「 The Khaeng 」on 03 Oct 2017 :)
 */


fun View?.setMargin(@Px left: Int = -1,
                    @Px top: Int = -1,
                    @Px right: Int = -1,
                    @Px bottom: Int = -1) {
    val marginParams = this?.layoutParams as ViewGroup.MarginLayoutParams
    marginParams.setMargins(
            if (left == -1) marginParams.leftMargin else left,
            if (top == -1) marginParams.topMargin else top,
            if (right == -1) marginParams.rightMargin else right,
            if (bottom == -1) marginParams.bottomMargin else bottom)
    this.requestLayout()
}


fun View?.addMargin(@Px left: Int = -1,
                    @Px top: Int = -1,
                    @Px right: Int = -1,
                    @Px bottom: Int = -1) {
    val marginParams = this?.layoutParams as ViewGroup.MarginLayoutParams
    marginParams.setMargins(
            if (left == -1) marginParams.leftMargin else marginParams.leftMargin + left,
            if (top == -1) marginParams.topMargin else marginParams.topMargin + top,
            if (right == -1) marginParams.rightMargin else marginParams.rightMargin + right,
            if (bottom == -1) marginParams.bottomMargin else marginParams.bottomMargin + bottom)
    this.requestLayout()
}




