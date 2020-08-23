package com.flaviotps.durmabem.custom.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.commons.MediaPlayerPool


class SoundService : Service() {

    private var binder = SoundServiceBinder()
    private lateinit var mediaPlayerPool: MediaPlayerPool

    inner class SoundServiceBinder : Binder() {
        fun getService(): SoundService {
            return this@SoundService
        }
        fun getMediaPlayerPool(): MediaPlayerPool {
            return mediaPlayerPool
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.i("SoundService", "onStartCommand")
        return START_STICKY
    }


    override fun onCreate() {
        super.onCreate()
        Log.i("SoundService", "onCreate")
        createNotification()
        mediaPlayerPool = MediaPlayerPool()

    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("SoundService", "onBind")
        return binder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("SoundService", "onRebind")
    }

    private fun createNotification(){
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
