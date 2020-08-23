package com.flaviotps.durmabem.main.viewmodel

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaviotps.durmabem.custom.services.SoundService

class ServiceViewModel: ViewModel() {

    val serviceConnection: ServiceConnection by lazy { createServiceConnection() }
    var soundServiceBinder: SoundService.SoundServiceBinder? = null
    var onServiceAvailable = MutableLiveData<SoundService.SoundServiceBinder?>(null)

    private fun createServiceConnection(): ServiceConnection{
        return object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                onServiceAvailable.postValue(null)
            }
            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                soundServiceBinder = (binder as SoundService.SoundServiceBinder)
                onServiceAvailable.postValue(soundServiceBinder)
            }
        }
    }

    fun onServiceAvailable(): MutableLiveData<SoundService.SoundServiceBinder?> {
        return onServiceAvailable
    }
}