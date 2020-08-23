package com.flaviotps.durmabem.custom.model

import android.media.MediaPlayer

class SoundPlayer(var soundModel:SoundModel, var mediaPlayer: MediaPlayer){

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
        return soundModel.soundResource
    }

    fun volume(soundModel: SoundModel) {
        this.soundModel = soundModel
        mediaPlayer.setVolume(this.soundModel.volume, this.soundModel.volume)
    }
}