package com.flaviotps.durmabem.custom.model

import android.content.Context
import android.media.MediaPlayer
import java.lang.Exception

class SoundPlayer(var context: Context, var soundInfo:SoundInfo){

    var mediaPlayer: MediaPlayer? = null

    fun isPlaying(): Boolean {
        mediaPlayer?.isPlaying?.let {
            return it
        }
        return false
    }

    fun play(){
        mediaPlayer = MediaPlayer.create(context, soundInfo.soundResource)
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }
    }
    fun pause(){
            mediaPlayer?.pause()
    }

    fun getId(): Int {
        return soundInfo.soundResource
    }

    fun stop(){
        try{
            mediaPlayer?.stop()
            mediaPlayer?.release()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun volume(soundInfo: SoundInfo) {
        this.soundInfo = soundInfo
        mediaPlayer?.setVolume(this.soundInfo.volume, this.soundInfo.volume)
    }
}