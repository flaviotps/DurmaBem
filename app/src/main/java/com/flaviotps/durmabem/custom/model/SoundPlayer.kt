package com.flaviotps.durmabem.custom.model

import android.media.MediaPlayer

class SoundPlayer(var soundInfo:SoundInfo, var mediaPlayer: MediaPlayer){

    fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    fun play(){
        mediaPlayer.start()
    }
    fun pause(){
        if(mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        }
    }

    fun getId(): Int {
        return soundInfo.soundResource
    }

    fun volume(soundInfo: SoundInfo) {
        this.soundInfo = soundInfo
        mediaPlayer.setVolume(this.soundInfo.volume, this.soundInfo.volume)
    }
}