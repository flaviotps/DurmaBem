package com.flaviotps.durmabem.custom.commons

import com.flaviotps.durmabem.custom.model.SoundInfo
import com.flaviotps.durmabem.custom.model.SoundPlayer
import com.flaviotps.durmabem.custom.model.SoundPool

class MediaPlayerPoolManager {

    private var soundPool: SoundPool = SoundPool()

    fun getSoundPool(): SoundPool? {
        return soundPool
    }

    fun setAuthor(author:String){
        soundPool.author = author
    }

    fun removeAll(){
        soundPool.sounds.forEach {
            it.mediaPlayer.stop()
        }
        soundPool.sounds.clear()
    }

    fun setSoundPool(soundPool: SoundPool){
        this.soundPool = soundPool
        this.soundPool.sounds = soundPool.sounds
        this.soundPool.author = soundPool.author
    }

    fun removePaused(){
        soundPool.sounds.forEach {
            if(!it.isPlaying()) {
                it.stop()
            }
        }
    }

    fun addMediaPlayer (soundPlayer: SoundPlayer): Boolean {
        if(find(soundPlayer.getId()) == null){
            soundPool.apply {
                sounds.apply{
                    add(soundPlayer)
                    return true
                }
            }
        }
        return false
    }

    fun playAll(){
        soundPool.sounds.forEach {
            if(!it.isPlaying()) {
                it.play()
            }
        }
    }

    fun pauseAll(){
        soundPool.sounds.forEach {
            if(it.isPlaying()) {
                it.pause()
            }
        }
    }

    fun pause(soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.pause()
    }

    fun play(soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.play()
    }

    fun isPlayingAny(): Boolean? {
            return soundPool.sounds.any { it.isPlaying() }
    }

    fun find(res:Int): SoundPlayer?{
       return soundPool.sounds.find {
           it.getId() == res
       }
    }

    fun volume(soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.volume(soundInfo)
    }
}