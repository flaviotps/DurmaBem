package com.flaviotps.durmabem.custom.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.commons.MediaPlayerPoolManager
import com.flaviotps.durmabem.custom.ext.setDrawableById


class SoundNotification(
    private var context: Context,
    private var service: SoundService,
    private var mediaPlayerPoolManager: MediaPlayerPoolManager
) {

    private var notificationManager: NotificationManager? = null

    private fun getChannel(): NotificationChannel? {
       var channel: NotificationChannel? = null
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
        return channel
    }
    fun create() {

        //CREATE NOTIFICATION CHANNEL
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        getChannel()?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager?.createNotificationChannel(this)
            }
        }

        val contentView = getRemoteView()

        val notification =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                .setContentTitle(context.getString(R.string.app_name))
                .setCustomContentView(contentView)
                .setAutoCancel(false)
                .setContent(contentView)
                .setOnlyAlertOnce(true)
                .build()

        service.startForeground(SoundService.NOTIFICATION_FLAG, notification)
        notificationManager?.notify(SoundService.NOTIFICATION_FLAG, notification)
    }

    private fun getRemoteView(): RemoteViews {
        //CREATE CUSTOM NOTIFICATION
        val contentView =
            RemoteViews(context.packageName, R.layout.notification)
        mediaPlayerPoolManager.getSoundPool()?.apply {
            // contentView.setTextViewText(R.id.autor, author)
            contentView.setTextViewText(R.id.name, "Soft Rain")
            contentView.setDrawableById(
                R.id.icon, "nature", context.resources, context.packageName
            )
        }

        //SETUP BUTTONS
        // buttonsIntent.putExtra("do_action", "play")
        mediaPlayerPoolManager.isPlayingAny()?.let {
            if (it) {
                contentView.setDrawableById(
                    R.id.play_pause_icon,
                    "ic_pause_black_24dp",
                    context.resources,
                    context.packageName
                )
            } else {
                contentView.setDrawableById(
                    R.id.play_pause_icon,
                    "ic_play_arrow_white_24dp",
                    context.resources,
                    context.packageName
                )
            }
        }

        contentView.setOnClickPendingIntent(
            R.id.play, PendingIntent.getService(
                context, 0, Intent(
                    SoundService.ACTION_PLAY
                ), 0
            )
        )
        contentView.setOnClickPendingIntent(
            R.id.close, PendingIntent.getService(
                context, 0, Intent(
                    SoundService.ACTION_CLOSE
                ), 0
            )
        )
        return contentView
    }

    companion object{
        const val CHANNEL_ID = "soundChannel"
    }

}