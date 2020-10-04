package com.flaviotps.durmabem.custom.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.widget.Toast

class SoundPlayer(var soundInfo: SoundInfo){

    var mediaPlayer1: MediaPlayer? = null
    var mediaPlayer2: MediaPlayer? = null
    private var isSoundPlaying : Boolean = false

    fun isPlaying(): Boolean {
        return isSoundPlaying
    }

    fun play(context: Context) {
        val mediaPath = Uri.parse("android.resource://" + context.packageName + "/" + soundInfo.soundResource)
            mediaPlayer1 = MediaPlayer()
            mediaPlayer1?.setDataSource(context, mediaPath)
            mediaPlayer1?.prepareAsync()
            mediaPlayer1?.setOnPreparedListener {
                mediaPlayer1?.start()
                handlerMedia2()
            }
            mediaPlayer1?.setOnErrorListener { mp, what, extra ->
                Toast.makeText(context,"Error", Toast.LENGTH_SHORT).show()
                return@setOnErrorListener false
            }

            mediaPlayer2 = MediaPlayer()
            mediaPlayer2?.setDataSource(context, mediaPath)
            mediaPlayer2?.prepare()

        isSoundPlaying = true
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
        val handler = Handler()
        (mediaPlayer1?.duration?.minus(soundInfo.loopOffset))?.toLong()?.let { delay ->
            handler.postDelayed({
                mediaPlayer2?.start()
                handlerMedia1()
            },
                delay
            )
        }
    }

    private fun handlerMedia1(){
        val handler = Handler()
        (mediaPlayer2?.duration?.minus(soundInfo.loopOffset))?.toLong()?.let { delay ->
            handler.postDelayed({
                mediaPlayer1?.start()
                handlerMedia2()
            },
                delay
            )
        }
    }
}