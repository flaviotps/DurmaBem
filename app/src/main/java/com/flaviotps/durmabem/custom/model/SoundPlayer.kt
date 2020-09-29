package com.flaviotps.durmabem.custom.model

import android.content.Context
import android.media.MediaPlayer
import java.lang.Exception

class SoundPlayer(var soundInfo:SoundInfo){

    var mediaPlayer: MediaPlayer? = null
    private var isSoundPlaying : Boolean = false

    fun isPlaying(): Boolean {
        return isSoundPlaying
    }

    fun play(context:Context){
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, soundInfo.soundResource)
            isSoundPlaying = true
            mediaPlayer?.setOnPreparedListener {
                mediaPlayer?.start()
            }
            mediaPlayer?.setOnErrorListener{mp, what, extra ->
                isSoundPlaying = false
                return@setOnErrorListener false
            }
        }else{
            isSoundPlaying = true
            mediaPlayer?.start()
        }

    }
    fun pause(){
        isSoundPlaying = false
        mediaPlayer?.pause()
    }

    fun getId(): Int {
        return soundInfo.soundResource
    }

    fun stop(){
        try{
            isSoundPlaying = false
            mediaPlayer?.stop()
            mediaPlayer = null
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun volume(soundInfo: SoundInfo) {
        this.soundInfo = soundInfo
        mediaPlayer?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
    }
}