package com.thekhaeng.systemui.base

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.thekhaeng.systemui.R
import kotlinx.android.synthetic.main.activity_all.*
import kotlinx.android.synthetic.main.activity_all.btn_back as btnBack
import kotlinx.android.synthetic.main.activity_all.tv_debug_android_content_root as tvAndroidContentRoot
import kotlinx.android.synthetic.main.activity_all.tv_debug_android_content_root_position as tvAndroidContentRootPosition
import kotlinx.android.synthetic.main.activity_all.tv_debug_android_decor_view as tvDecor
import kotlinx.android.synthetic.main.activity_all.tv_debug_android_decor_view_position as tvDecorPosition
import kotlinx.android.synthetic.main.activity_all.tv_debug_content_root as tvContentRoot
import kotlinx.android.synthetic.main.activity_all.tv_debug_content_root_position as tvContentRootPosition
import kotlinx.android.synthetic.main.activity_all.tv_debug_density as tvDensity
import kotlinx.android.synthetic.main.activity_all.tv_debug_insets as tvInsets
import kotlinx.android.synthetic.main.activity_all.tv_debug_title as tvTitle


abstract class CommonInsetsActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override
    fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_all)
        if (requestFullscreenFlags()) {
            if (Build.VERSION.SDK_INT >= 20) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            }
        }

        tvTitle.text = javaClass.simpleName

        btnBack.setOnClickListener { onBackPressed() }


        window.decorView.addOnLayoutChangeListener { view, _, _, _, _, _, _, _, _ ->
            val array = IntArray(2)
            view.getLocationOnScreen(array)

            tvDecorPosition.text = "Position (x,y) = (${array[0]}, ${array[1]})"
            tvDecor.text = "Size = ${view.width} x ${view.height}"
        }


        findViewById<View>(android.R.id.content).addOnLayoutChangeListener { view, _, _, _, _, _, _, _, _ ->
            val array = IntArray(2)
            view.getLocationOnScreen(array)

            tvAndroidContentRootPosition.text = "Position (x,y) = (${array[0]}, ${array[1]})"
            tvAndroidContentRoot.text = "Size = ${view.width} x ${view.height}"
        }

        root.addOnLayoutChangeListener { view, _, _, _, _, _, _, _, _ ->
            val array = IntArray(2)
            view.getLocationOnScreen(array)

            tvContentRootPosition.text = "Position (x,y) = (${array[0]}, ${array[1]})"
            tvContentRoot.text = "Size = ${view.width} x ${view.height}"
        }

        var isSetPadding = false
        ViewCompat.setOnApplyWindowInsetsListener(root, { _, windowInsets ->
            if (!isSetPadding) {
                isSetPadding = true //fix bug: android P emulator call twice
                tvInsets.text = "Insets [ ${windowInsets.systemWindowInsetLeft}, " +
                        "${windowInsets.systemWindowInsetRight}, " +
                        "${windowInsets.systemWindowInsetTop}, " +
                        "${windowInsets.systemWindowInsetBottom} ]"

                if (correct()) {
                    root.setPadding(root.paddingLeft + windowInsets.systemWindowInsetLeft,
                            root.paddingTop + windowInsets.systemWindowInsetTop,
                            root.paddingRight + windowInsets.systemWindowInsetRight,
                            root.paddingBottom + windowInsets.systemWindowInsetBottom)

                    btnBack.addMargin(left = windowInsets.systemWindowInsetLeft,
                            top = windowInsets.systemWindowInsetTop,
                            right = windowInsets.systemWindowInsetRight)
                }
            }
            if (consumeInsets()) windowInsets.consumeSystemWindowInsets() else windowInsets
        })




        tvDensity.text = "${resources.displayMetrics.density} ${getDensityString(resources)}"
    }

    abstract fun correct(): Boolean

    abstract fun consumeInsets(): Boolean

    abstract fun requestFullscreenFlags(): Boolean

    private fun getDensityString(resources: Resources): String {
        return when (resources.displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> "ldpi"
            DisplayMetrics.DENSITY_MEDIUM -> "mdpi"
            DisplayMetrics.DENSITY_HIGH -> "hdpi"
            DisplayMetrics.DENSITY_XHIGH -> "xhdpi"
            DisplayMetrics.DENSITY_XXHIGH -> "xxhdpi"
            DisplayMetrics.DENSITY_XXXHIGH -> "xxxhdpi"
            DisplayMetrics.DENSITY_TV -> "tvdpi"
            else -> "unknown"
        }
    }

}