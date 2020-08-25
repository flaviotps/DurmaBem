package com.flaviotps.durmabem.custom.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.flaviotps.durmabem.custom.commons.MediaPlayerPoolManager


class SoundService : Service() {

    private var binder = SoundServiceBinder()
    private var mediaPlayerPoolManager: MediaPlayerPoolManager =  MediaPlayerPoolManager()
    private var soundNotification:SoundNotification? = null

    inner class SoundServiceBinder : Binder() {
        fun getService(): SoundService {
            return this@SoundService
        }
        fun getMediaPlayerPool(): MediaPlayerPoolManager {
            return mediaPlayerPoolManager
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_PLAY -> {
                mediaPlayerPoolManager.isPlayingAny()?.let {
                    if(it){
                        mediaPlayerPoolManager.pauseAll()
                    }else{
                        mediaPlayerPoolManager.playAll()
                    }
                }
                soundNotification?.create()
            }
            ACTION_CLOSE -> {
                mediaPlayerPoolManager.pauseAll()
                mediaPlayerPoolManager.removePaused()
                stopForeground(true)
            }
        }
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayerPoolManager.setAuthor("Flavio Telles")
        soundNotification = SoundNotification(this,this,mediaPlayerPoolManager)
        soundNotification?.create()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    companion object{
        const val ACTION_PLAY = "ACTION_PLAY_PAUSE"
        const val ACTION_CLOSE = "ACTION_CLOSE"
        const val NOTIFICATION_FLAG = 1
    }
}
