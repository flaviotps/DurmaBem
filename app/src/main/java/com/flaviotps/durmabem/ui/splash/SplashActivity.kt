package com.flaviotps.durmabem.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.ui.preset.PresetsActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, PresetsActivity::class.java))
    }
}
