package com.flaviotps.sleepwell.services


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.flaviotps.sleepwell.*
import com.flaviotps.sleepwell.ext.setDrawableById
import com.flaviotps.sleepwell.managers.MediaPlayerPoolManager
import com.flaviotps.sleepwell.ui.splash.SplashActivity


class SoundService : LifecycleService() {

    private var chronometerTime: MutableLiveData<Long> = MutableLiveData(0)
    private lateinit var mediaPlayerPoolManager: MediaPlayerPoolManager
    private lateinit var notificationManager: NotificationManager
    private var binder = SoundServiceBinder()
    private var timer: CountDownTimer? = null

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mediaPlayerPoolManager = MediaPlayerPoolManager()
        getPoolManager().isAnyPlayingLiveData().observe(this, Observer { createNotification() })
    }

    fun getChronometerTime(): MutableLiveData<Long> {
        return chronometerTime
    }

    fun stopTimer() {
        chronometerTime.postValue(0)
         timer?.cancel()
    }

    fun startTimer(totalTime : Long){
        chronometerTime.postValue(totalTime)
         timer = object : CountDownTimer(totalTime,1000) {
            override fun onTick(millisUntilFinished: Long) {
                var tempValue: Long = 0
                chronometerTime.value?.let {
                    tempValue = it
                }
                tempValue -= 1000
                chronometerTime.postValue(tempValue)
            }

            override fun onFinish() {
                mediaPlayerPoolManager.stopAll()
                stopForeground(true)
            }
        }.start()
    }

    inner class SoundServiceBinder : Binder() {
        fun getService(): SoundService {
            return this@SoundService
        }
    }
    fun getPoolManager(): MediaPlayerPoolManager {
        return mediaPlayerPoolManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_PLAY -> {
                if(getPoolManager().isPlayingAny()) {
                    getPoolManager().stopAll()
                }else{
                    getPoolManager().playAll(this)
                }
            }
            ACTION_CLOSE -> {
                getPoolManager().stopAll()
                stopSelf()
            }
            ACTION_EDIT -> {
               Intent(this, SplashActivity::class.java).apply {
                    putExtra(NAVIGATE_TO, R.id.mixerFragment)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(this)
                }

            }
        }
        return START_STICKY
    }

    private fun createNotification() {
        val channel: NotificationChannel?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(channel)
        }

        val notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_note_black_24dp)
                .setContentTitle(getString(R.string.app_name))
                .setCustomContentView(getRemoteView())
                .setAutoCancel(false)
                .setContent(getRemoteView())
                .setOnlyAlertOnce(true)
                .build()
        startForeground(NOTIFICATION_FLAG, notification)
        notificationManager.notify(NOTIFICATION_FLAG, notification)
    }


    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        mediaPlayerPoolManager.getSoundPool()?.apply {
            if(sounds.isEmpty()){
                stopForeground(true)
            }
        }
        return super.onUnbind(intent)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        mediaPlayerPoolManager.getSoundPool()?.apply {
            if(sounds.isEmpty()){
                stopForeground(true)
            }
        }
    }

    private fun getRemoteView(): RemoteViews {
        val contentView = RemoteViews(packageName, R.layout.notification_player)
        getPoolManager().isPlayingAny().let {
            if (it) {
                contentView.setDrawableById(
                    R.id.notification_play,
                    "ic_pause_black_24dp",
                    resources,
                    packageName
                )
            } else {
                contentView.setDrawableById(
                    R.id.notification_play,
                    "ic_play_arrow_white_24dp",
                    resources,
                    packageName
                )
            }
        }

        contentView.setOnClickPendingIntent(
            R.id.notification_play, PendingIntent.getService(
                this, 0, Intent(
                    ACTION_PLAY
                ), 0
            )
        )

        contentView.setOnClickPendingIntent(
            R.id.notification_stop, PendingIntent.getService(
                this, 0, Intent(
                    ACTION_CLOSE
                ), 0
            )
        )

        contentView.setOnClickPendingIntent(
            R.id.notification_edit, PendingIntent.getService(
                this, 0, Intent(
                    ACTION_EDIT
                ), 0
            )
        )
        return contentView
    }
}
