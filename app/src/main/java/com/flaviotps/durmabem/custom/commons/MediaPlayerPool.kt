package com.flaviotps.durmabem.custom.commons

import com.flaviotps.durmabem.custom.model.SoundPlayer

class MediaPlayerPool {

    private var mediaPlayers: MutableList<SoundPlayer>? = null

    fun addMediaPlayer (soundPlayer: SoundPlayer): Boolean {
        if(mediaPlayers == null){
            mediaPlayers = mutableListOf()
        }

        if(find(soundPlayer.getId()) == null){
            soundPlayer.let {
                mediaPlayers?.add(soundPlayer)
                return true
            }
        }

        return false
    }

    fun playAll(){
        mediaPlayers?.forEach {
            if(!it.isPlaying()) {
                it.play()
            }
        }
    }

    fun pauseAll(){
        mediaPlayers?.forEach {
            if(it.isPlaying()) {
                it.pause()
            }
        }
    }

    fun pause(soundPlayer: SoundPlayer){
        find(soundPlayer.getId())?.pause()
    }

    fun stop(soundPlayer: SoundPlayer){
        find(soundPlayer.getId())?.stop()
        mediaPlayers?.remove(soundPlayer)
    }

    fun play(soundPlayer: SoundPlayer){
        find(soundPlayer.getId())?.play()
    }

    fun isPlayingAny(): Boolean? {
            return mediaPlayers?.any { it.isPlaying() }
    }

    fun find(res:Int): SoundPlayer?{
       return mediaPlayers?.find {
           it.getId() == res
       }
    }
}