package com.flaviotps.sleepwell.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.flaviotps.sleepwell.widgets.SoundNotification
import com.flaviotps.sleepwell.managers.MediaPlayerPoolManager


class SoundService : Service() {

    private var binder = SoundServiceBinder()
    private var mediaPlayerPoolManager: MediaPlayerPoolManager? = null
    private var soundNotification: SoundNotification? = null

    inner class SoundServiceBinder : Binder() {
        fun getService(): SoundService {
            return this@SoundService
        }
    }

    fun getMediaPoolManager(): MediaPlayerPoolManager? {
        return mediaPlayerPoolManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_PLAY -> {
                mediaPlayerPoolManager?.isPlayingAny().let {
                    if(it!!){
                        mediaPlayerPoolManager?.pauseAll()
                    }else{
                        mediaPlayerPoolManager?.playAll(this)
                    }
                }
                mediaPlayerPoolManager?.getSoundPool()?.let { soundNotification?.create(it) }
            }
            ACTION_CLOSE -> {
                mediaPlayerPoolManager?.stopAll()
                stopForeground(true)
            }
        }
        return START_STICKY
    }

    fun createNotification(){
        mediaPlayerPoolManager?.getSoundPool()?.let {
            soundNotification = SoundNotification(this, this, mediaPlayerPoolManager!!)
            soundNotification?.create(it)
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun startMediaManager(){
        mediaPlayerPoolManager = MediaPlayerPoolManager()
    }

    companion object{
        const val ACTION_PLAY = "ACTION_PLAY_PAUSE"
        const val ACTION_CLOSE = "ACTION_CLOSE"
        const val NOTIFICATION_FLAG = 1
    }
}
