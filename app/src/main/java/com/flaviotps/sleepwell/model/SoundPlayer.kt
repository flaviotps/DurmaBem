package com.flaviotps.sleepwell.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import com.flaviotps.sleepwell.ANDROID_RESOURCE
import java.util.*

class SoundPlayer(var soundInfo: SoundInfo){

    private var mediaPlayer1: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    private var isSoundPlaying : Boolean = false
    private var timer1:Timer? = null
    private var timer2:Timer? = null

    fun isPlaying(): Boolean {
        return isSoundPlaying
    }

    fun play(context: Context) {
            isSoundPlaying = true
        Thread {
            val mediaPath =
                Uri.parse(ANDROID_RESOURCE + context.packageName + "/" + soundInfo.soundResource)
            if (mediaPlayer1 == null) {
                mediaPlayer1 = MediaPlayer()
                mediaPlayer1?.setVolume(soundInfo.volume, soundInfo.volume)
                mediaPlayer1?.setDataSource(context, mediaPath)
                mediaPlayer1?.prepareAsync()
                mediaPlayer1?.setOnPreparedListener {

                    mediaPlayer1?.duration?.minus(soundInfo.loopOffset)?.toLong()?.let { delay ->
                        val task1 = object : TimerTask() {
                            override fun run() {
                                mediaPlayer1?.start()
                            }
                        }

                        timer1 = Timer()
                        timer1?.schedule(task1, 0, delay)
                    }

                }
                mediaPlayer1?.setOnErrorListener { mp, what, extra ->
                    Toast.makeText(context, "Error Code 1", Toast.LENGTH_SHORT).show()
                    return@setOnErrorListener false
                }
            } else {
                mediaPlayer1?.start()
            }

            if (mediaPlayer2 == null) {
                mediaPlayer2?.setOnErrorListener { mp, what, extra ->
                    Toast.makeText(context, "Error Code 2", Toast.LENGTH_SHORT).show()
                    return@setOnErrorListener false
                }
                mediaPlayer2 = MediaPlayer()
                mediaPlayer2?.setVolume(soundInfo.volume, soundInfo.volume)
                mediaPlayer2?.setDataSource(context, mediaPath)
                mediaPlayer2?.prepare()
                mediaPlayer2?.duration?.minus(soundInfo.loopOffset)?.toLong()?.let { delay ->
                    val task1 = object : TimerTask() {
                        override fun run() {
                            mediaPlayer2?.start()
                        }
                    }
                    timer2 = Timer()
                    timer2?.schedule(task1, delay, delay)
                }
            }
        }.start()
    }

    fun pause(){
        isSoundPlaying = false
        mediaPlayer1?.pause()
        mediaPlayer2?.pause()
    }

    fun getId(): Int {
        return soundInfo.soundResource
    }

    fun stop(){
        try{
            isSoundPlaying = false
            mediaPlayer1?.stop()
            mediaPlayer1 = null
            mediaPlayer2?.stop()
            mediaPlayer2 = null
            timer1?.cancel()
            timer1?.purge()
            timer1 = null
            timer2?.cancel()
            timer2?.purge()
            timer2 = null
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun volume(soundInfo: SoundInfo) {
        this.soundInfo = soundInfo
        mediaPlayer1?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
        mediaPlayer2?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
    }
}