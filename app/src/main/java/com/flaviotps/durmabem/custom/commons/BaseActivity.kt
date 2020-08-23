package com.flaviotps.durmabem.custom.commons

import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.flaviotps.durmabem.custom.services.SoundService

open class BaseActivity : AppCompatActivity() {


    fun startSoundService(serviceConnection: ServiceConnection) {
        try{
            val intent = Intent(this, SoundService::class.java)
            ContextCompat.startForegroundService(this, intent)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}