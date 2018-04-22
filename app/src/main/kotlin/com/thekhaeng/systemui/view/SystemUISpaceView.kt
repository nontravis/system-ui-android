package com.thekhaeng.systemui.view

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View
import android.view.WindowInsets
import com.thekhaeng.systemui.R


/**
 * Created by「 The Khaeng 」on 21 Apr 2018 :)
 */
class SystemUISpaceView : View {

    companion object {
        const val TOP = 0
        const val LEFT = 1
        const val RIGHT = 2
        const val BOTTOM = 3
    }

    var topSystemUI: Int = 0
    var bottomSystemUI: Int = 0
    var leftSystemUI: Int = 0
    var rightSystemUI: Int = 0
    var isConsumedInsets: Boolean = false
    var systemUI: Int = TOP

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr) {
        setupStyleables(attrs, defStyleAttr, 0)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        setupStyleables(attrs, defStyleAttr, defStyleRes)
    }


    protected fun setupStyleables(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        attrs?.let {
            val attrArray = context.obtainStyledAttributes(attrs, R.styleable.StatusBarSpaceView, defStyleRes, 0)
            isConsumedInsets = attrArray.getBoolean(R.styleable.StatusBarSpaceView_isConsumeInsets, false)
            systemUI = attrArray.getInt(R.styleable.StatusBarSpaceView_systemUI, TOP)
            attrArray.recycle()
        }
    }


    @Suppress("OverridingDeprecatedMember")
    override
    fun fitSystemWindows(insets: Rect): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
            topSystemUI = insets.top
            leftSystemUI = insets.left
            rightSystemUI = insets.right
            bottomSystemUI = insets.bottom
        }
        return isConsumedInsets
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
    override
    fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets {
        topSystemUI = insets?.systemWindowInsetTop ?: 0
        leftSystemUI = insets?.systemWindowInsetLeft ?: 0
        rightSystemUI = insets?.systemWindowInsetRight ?: 0
        bottomSystemUI = insets?.systemWindowInsetBottom ?: 0

        return if (isConsumedInsets) {
            super.onApplyWindowInsets(insets?.consumeSystemWindowInsets())
        } else {
            super.onApplyWindowInsets(insets)
        }
    }


    override
    fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newHeightMeasureSpec = 0
        var newWidthMeasureSpec = 0
        var width = 0
        var height = 0
        when (systemUI) {
            TOP -> {
                width = View.MeasureSpec.getSize(widthMeasureSpec)
                height = topSystemUI
                newWidthMeasureSpec = widthMeasureSpec
                newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                        topSystemUI,
                        View.MeasureSpec.EXACTLY
                )
            }
            LEFT -> {
                width = leftSystemUI
                height = View.MeasureSpec.getSize(heightMeasureSpec)
                newWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                        leftSystemUI,
                        View.MeasureSpec.EXACTLY
                )
                newHeightMeasureSpec = heightMeasureSpec
            }
            RIGHT -> {
                width = rightSystemUI
                height = View.MeasureSpec.getSize(heightMeasureSpec)
                newWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                        rightSystemUI,
                        View.MeasureSpec.EXACTLY
                )
                newHeightMeasureSpec = heightMeasureSpec
            }
            BOTTOM -> {
                width = View.MeasureSpec.getSize(widthMeasureSpec)
                height = bottomSystemUI
                newWidthMeasureSpec = widthMeasureSpec
                newHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                        bottomSystemUI,
                        View.MeasureSpec.EXACTLY
                )
            }
        }
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
        setMeasuredDimension(width, height)
    }

}
