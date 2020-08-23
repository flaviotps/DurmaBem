package com.flaviotps.durmabem.custom.commons

import com.flaviotps.durmabem.custom.model.SoundModel
import com.flaviotps.durmabem.custom.model.SoundPlayer

class MediaPlayerPool {

    private var soundPlayers: MutableList<SoundPlayer>? = null

    fun addMediaPlayer (soundPlayer: SoundPlayer): Boolean {
        if(soundPlayers == null){
            soundPlayers = mutableListOf()
        }

        if(find(soundPlayer.getId()) == null){
            soundPlayer.let {
                soundPlayers?.add(soundPlayer)
                return true
            }
        }

        return false
    }

    fun playAll(){
        soundPlayers?.forEach {
            if(!it.isPlaying()) {
                it.play()
            }
        }
    }

    fun pauseAll(){
        soundPlayers?.forEach {
            if(it.isPlaying()) {
                it.pause()
            }
        }
    }

    fun pause(soundModel: SoundModel){
        find(soundModel.soundResource)?.pause()
    }

    fun play(soundModel: SoundModel){
        find(soundModel.soundResource)?.play()
    }

    fun isPlayingAny(): Boolean? {
            return soundPlayers?.any { it.isPlaying() }
    }

    fun find(res:Int): SoundPlayer?{
       return soundPlayers?.find {
           it.getId() == res
       }
    }

    fun volume(soundModel: SoundModel){
        find(soundModel.soundResource)?.volume(soundModel)
    }
}