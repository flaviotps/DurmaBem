package com.flaviotps.sleepwell.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.flaviotps.sleepwell.services.SoundService

open class BaseServiceFragment : Fragment() {
    private val serviceConnection: ServiceConnection by lazy { createServiceConnection() }
    private var soundServiceBinder: SoundService.SoundServiceBinder? = null
    private var onServiceAvailable = MutableLiveData<SoundService.SoundServiceBinder?>(null)

    private fun startSoundService() {
        val intent = Intent(activity, SoundService::class.java)
        context?.let {
            ContextCompat.startForegroundService(it, intent)
            activity?.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSoundService()
    }

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

    override fun onDestroy() {
        super.onDestroy()
        activity?.unbindService(serviceConnection)
    }
}