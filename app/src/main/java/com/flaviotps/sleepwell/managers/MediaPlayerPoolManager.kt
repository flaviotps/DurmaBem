package com.flaviotps.sleepwell.managers

import android.content.Context
import com.flaviotps.sleepwell.model.SoundInfo
import com.flaviotps.sleepwell.model.SoundPlayer
import com.flaviotps.sleepwell.model.SoundPool

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
            it.stop()
        }
        soundPool.sounds.clear()
    }

    fun setSoundPool(soundPool: SoundPool){
        this.soundPool = soundPool
        this.soundPool.sounds = soundPool.sounds
        this.soundPool.author = soundPool.author
        this.soundPool.title = soundPool.title
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

    fun playAll(context: Context){
        soundPool.sounds.forEach {
                it.play(context)
        }
    }

    fun pauseAll(){
        soundPool.sounds.forEach {
            if(it.isPlaying()) {
                it.pause()
            }
        }
    }

    fun stopAll(){
        soundPool.sounds.forEach {
                it.stop()
        }
    }

    fun stop(soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.stop()
    }

    fun pause(soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.pause()
    }

    fun play(context:Context,soundInfo: SoundInfo){
        find(soundInfo.soundResource)?.play(context)
    }

    fun isPlayingAny(): Boolean {
        var found = false
        soundPool.sounds.forEach {
            if(it.isPlaying()){
                found = true
            }
        }
        return found
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