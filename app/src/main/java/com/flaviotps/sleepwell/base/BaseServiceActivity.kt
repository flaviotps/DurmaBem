package com.flaviotps.sleepwell.base

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.services.SoundService
import kotlinx.android.synthetic.main.toolbar_custom.*


open class BaseServiceActivity : AppCompatActivity() {

    private val serviceConnection: ServiceConnection by lazy { createServiceConnection() }
    private var soundServiceBinder: SoundService.SoundServiceBinder? = null
    private var onServiceAvailable = MutableLiveData<SoundService.SoundServiceBinder?>(null)

    private fun startSoundService() {
            val intent = Intent(this, SoundService::class.java)
            ContextCompat.startForegroundService(this, intent)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPlayer()
        startSoundService()
    }

    private fun initPlayer(){
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.toolbar_custom)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar_title.text = resources.getString(R.string.app_name)

        togglePlayPause()
    }

    private fun togglePlayPause(){
/*        val playAndPause = supportActionBar?.customView?.findViewById<ImageButton>(R.id.playAndPause)
       onServiceAvailable.observe(this, Observer {
            soundServiceBinder?.getMediaPlayerPool()?.isPlaying?.observe(this, Observer {
                if(it){
                    playAndPause?.setImageResource(R.drawable.ic_pause_black_24dp)
                }else{
                    playAndPause?.setImageResource(R.drawable.ic_play_arrow_white_24dp)
                }
            })
       })*/
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
        unbindService(serviceConnection)
    }
}