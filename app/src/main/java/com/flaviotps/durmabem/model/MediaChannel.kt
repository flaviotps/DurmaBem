package com.flaviotps.durmabem.model

import android.content.Context
import android.media.MediaPlayer
import android.media.PlaybackParams
import android.os.Build

class MediaChannel(val id:Int, context: Context, val soundModel: SoundModel) {
    var mediaPlayer: MediaPlayer = MediaPlayer.create(context,soundModel.sound).also {
        it.setVolume(soundModel.volume,soundModel.volume)
        it.isLooping = soundModel.loop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val playbackParams = it.playbackParams
            playbackParams.speed = soundModel.speed
            it.playbackParams = playbackParams
        }
    }
}