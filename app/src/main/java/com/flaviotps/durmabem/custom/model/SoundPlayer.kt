package com.flaviotps.durmabem.custom.model

import android.media.MediaPlayer

class SoundPlayer(var soundInfo:SoundModel,var mediaPlayer: MediaPlayer){

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

    fun stop(){
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}