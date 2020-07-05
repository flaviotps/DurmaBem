package com.flaviotps.durmabem.main.viewmodel

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.ViewModel
import com.flaviotps.durmabem.services.SoundService

class MainViewModel: ViewModel() {

    val serviceConnection: ServiceConnection by lazy { createServiceConnection() }

    lateinit var service: SoundService

    private fun createServiceConnection(): ServiceConnection{
        return object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
//
            }
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                service = (binder as SoundService.SoundServiceBinder).getService()
                service.playAll()
            }

        }
    }
}