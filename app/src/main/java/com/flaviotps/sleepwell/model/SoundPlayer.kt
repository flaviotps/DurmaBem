package com.flaviotps.sleepwell.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.widget.Toast

class SoundPlayer(var soundInfo: SoundInfo){

    private var mediaPlayer1: MediaPlayer? = null
    private var mediaPlayer2: MediaPlayer? = null
    private var handler1: Handler? = null
    private var handler2: Handler? = null
    private var isSoundPlaying : Boolean = false

    private var runnable1  = {
        mediaPlayer1?.start()
        handlerMedia2()
    }
    private var runnable2  = {
        mediaPlayer2?.start()
        handlerMedia1()
    }

    fun isPlaying(): Boolean {
        return isSoundPlaying
    }

    fun play(context: Context) {
        val mediaPath = Uri.parse("android.resource://" + context.packageName + "/" + soundInfo.soundResource)

        if(mediaPlayer1 == null){
            mediaPlayer1 = MediaPlayer()
            mediaPlayer1?.setDataSource(context, mediaPath)
            mediaPlayer1?.prepareAsync()
            mediaPlayer1?.setOnPreparedListener {
                mediaPlayer1?.start()
                handlerMedia2()
            }
        }else{
            mediaPlayer1?.start()
        }

        if(mediaPlayer2 == null){
            mediaPlayer1?.setOnErrorListener { mp, what, extra ->
                Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                return@setOnErrorListener false
            }
            mediaPlayer2 = MediaPlayer()
            mediaPlayer2?.setDataSource(context, mediaPath)
            mediaPlayer2?.prepare()
        }

        isSoundPlaying = true
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
            handler2?.postDelayed(runnable2,delay)
        }
    }

    private fun handlerMedia1(){
        handler1 = Handler()
        mediaPlayer2?.duration?.minus(soundInfo.loopOffset)?.toLong()?.let { delay ->
            handler1?.postDelayed(runnable1,delay)
        }
    }
}