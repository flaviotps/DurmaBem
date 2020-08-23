package com.flaviotps.durmabem.custom.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.commons.MediaPlayerPoolManager
import com.flaviotps.durmabem.custom.ext.setDrawableById


class SoundService : Service() {

    private var binder = SoundServiceBinder()
    private var mediaPlayerPoolManager: MediaPlayerPoolManager =  MediaPlayerPoolManager()

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
        Log.i("SoundService", "onStartCommand")
        return START_STICKY
    }


    override fun onCreate() {
        super.onCreate()
        Log.i("SoundService", "onCreate")
        mediaPlayerPoolManager.setAuthor("Flavio Telles")
        createNotification()

    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("SoundService", "onBind")
        return binder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("SoundService", "onRebind")
    }


    private fun createNotification() {
        val contentView = RemoteViews(packageName, R.layout.notification)

        mediaPlayerPoolManager.getSoundPool()?.apply {
            contentView.setTextViewText(R.id.autor, author)
            contentView.setTextViewText( R.id.soundname,"Soft Rain")
            contentView.setDrawableById(
                R.id.icon, "fire", resources, packageName
            )
        }

        val channelId = "soundChannel"
        //val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_more_vert_black_24dp)
                .setContentTitle(getString(R.string.app_name))
                .setCustomContentView(contentView)
                .setAutoCancel(false)
                //.setSound(defaultSoundUri)
                .setContent(contentView)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        // notificationManager.notify(1, notificationBuilder.build())

        startForeground(1, notificationBuilder.build())
    }


}
