package com.flaviotps.durmabem.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.model.MediaChannel
import com.flaviotps.durmabem.model.MixModel
import com.flaviotps.durmabem.model.SoundModel


class SoundService : Service() {

    private val binder = SoundServiceBinder()
    private val builder = SoundModel.SoundModelBuilder()
    private lateinit var mixer:MixModel

    inner class SoundServiceBinder : Binder() {
        fun getService(): SoundService {
            return this@SoundService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    fun playAll(){
        for(mediaChannel in mixer.mediaChannels){
            if(!mediaChannel.mediaPlayer.isPlaying){
                mediaChannel.mediaPlayer.start()
            }
        }
    }

    fun addSound(mediaChannel: MediaChannel){
        mixer.mediaChannels.add(mediaChannel)
        playAll()
    }

    fun removeSound(id:Int){
        val item = mixer.mediaChannels.find { it.id == id }
        item?.let {
            it.mediaPlayer.stop()
            it.mediaPlayer.release()
            mixer.mediaChannels.remove(it)
        }

    }

    fun removeSound(vararg ids:Int){
        for (id in ids) {
            val item = mixer.mediaChannels.find { it.id == id }
            item?.let {
                it.mediaPlayer.stop()
                it.mediaPlayer.release()
                mixer.mediaChannels.remove(it)
            }
        }
    }

    fun updateVolume(id:Int, volume:Float){
        mixer.mediaChannels.find { it.id == id }?.let {
            it.soundModel.volume = volume
            it.mediaPlayer.setVolume(volume,volume)
        }
    }

    fun updateSpeed(id:Int, speed:Float){
        mixer.mediaChannels.find { it.id == id }?.let {
            it.soundModel.speed = speed
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val playbackParams = it.mediaPlayer.playbackParams
                playbackParams.speed = speed
                it.mediaPlayer.playbackParams = playbackParams
            }
        }
    }


    override fun onCreate() {
        super.onCreate()

        val sound = builder
            .withSound(R.raw.rain)
            .withLoop(true)
            .withVolume(1.0f)
            .build()

        mixer = MixModel("FLAVIO")

        addSound(MediaChannel(0,this,sound))
        createNotification()

    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun createNotification(){
        if (Build.VERSION.SDK_INT >= 26) {
            val CHANNEL_ID = "my_channel_01"
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
            val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build()
            startForeground(1, notification)
        }
    }
}
