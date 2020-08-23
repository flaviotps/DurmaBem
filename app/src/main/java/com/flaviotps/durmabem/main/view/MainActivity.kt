package com.flaviotps.durmabem.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.main.viewmodel.ServiceViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ServiceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
