package com.flaviotps.durmabem.main.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.main.viewmodel.MainViewModel
import com.flaviotps.durmabem.model.MediaChannel
import com.flaviotps.durmabem.model.SoundModel
import com.flaviotps.durmabem.services.SoundService

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var button: Button
    lateinit var buttonFire: Button
    lateinit var buttonRain: Button
    private val builder = SoundModel.SoundModelBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        buttonRain = findViewById(R.id.button2)
        buttonFire = findViewById(R.id.button3)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        startSoundService()

        buttonRain.setOnClickListener {
            viewModel.service.addSound(MediaChannel(0,this,builder.withSound(R.raw.rain).build()))
        }

        buttonFire.setOnClickListener {
            viewModel.service.addSound(MediaChannel(1,this,builder.withSound(R.raw.fire).build()))
        }

        button.setOnClickListener {
            viewModel.service.removeSound(0,1)
        }
    }

    private fun startSoundService(){
        val intent = Intent(this, SoundService::class.java)
        ContextCompat.startForegroundService(this,intent)
        bindService(intent,viewModel.serviceConnection , Context.BIND_AUTO_CREATE)
    }
}
