package com.thekhaeng.systemui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.thekhaeng.systemui.insets.*

class MainActivity : AppCompatActivity() {

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onBasicClicked(view: View) {
        startActivity(Intent(this, BasicActivity::class.java))
    }

    fun onConsumedInsetsClicked(view: View) {
        startActivity(Intent(this, ConsumedInsetsActivity::class.java))
    }

    fun onConsumedInsetsCorrectClicked(view: View) {
        startActivity(Intent(this, ConsumedInsetsCorrectActivity::class.java))
    }

    fun onUnconsumedInsetsClicked(view: View) {
        startActivity(Intent(this, UnconsumedInsetsActivity::class.java))
    }

    fun onUnconsumedInsetsCorrectClicked(view: View) {
        startActivity(Intent(this, UnconsumedInsetsCorrectActivity::class.java))
    }
}
