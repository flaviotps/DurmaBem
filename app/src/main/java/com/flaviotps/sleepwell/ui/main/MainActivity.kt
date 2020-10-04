package com.flaviotps.sleepwell.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.base.BaseServiceActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseServiceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(findNavController(R.id.navFragment))
    }
}
