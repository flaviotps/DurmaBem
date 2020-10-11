package com.flaviotps.sleepwell.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.widget.Toast
import com.flaviotps.sleepwell.ANDROID_RESOURCE
import java.util.*

class SoundPlayer(var soundInfo: SoundInfo){

    private var mediaPlayer1: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    private var handler1: Handler? = null
    private var handler2: Handler? = null
    private var isSoundPlaying : Boolean = false

    private var runnable1  = {
        if(isSoundPlaying) {
            mediaPlayer1?.start()
            handlerMedia2()
        }
    }
    private var runnable2  = {
        if(isSoundPlaying) {
            mediaPlayer2?.start()
            handlerMedia1()
        }
    }

    fun isPlaying(): Boolean {
        return isSoundPlaying
    }

    fun play(context: Context) {
        Thread {
            isSoundPlaying = true
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

                        Timer().schedule(task1, 0, delay)
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
                    Timer().schedule(task1, delay, delay)
                }
            }
        }.start()
    }

    fun pause(){
        isSoundPlaying = false
        mediaPlayer1?.pause()
        mediaPlayer2?.pause()
        handler1?.removeCallbacks(runnable1)
        handler2?.removeCallbacks(runnable2)
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
            handler1?.removeCallbacks(runnable1)
            handler2?.removeCallbacks(runnable2)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun volume(soundInfo: SoundInfo) {
        this.soundInfo = soundInfo
        mediaPlayer1?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
        mediaPlayer2?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
    }

    private fun handlerMedia2(){
        handler2 = Handler()
        mediaPlayer1?.duration?.minus(soundInfo.loopOffset)?.toLong()?.let { delay ->
            handler2?.postDelayed(runnable2, delay)
        }
    }

    private fun handlerMedia1(){
        handler1 = Handler()
        mediaPlayer2?.duration?.minus(soundInfo.loopOffset)?.toLong()?.let { delay ->
            handler1?.postDelayed(runnable1, delay)
        }
    }
}