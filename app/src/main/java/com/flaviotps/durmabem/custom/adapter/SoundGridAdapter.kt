package com.flaviotps.durmabem.custom.adapter

import android.content.Context
import android.media.MediaPlayer
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.flaviotps.durmabem.custom.commons.MediaPlayerPool
import com.flaviotps.durmabem.custom.model.SoundModel
import com.flaviotps.durmabem.custom.model.SoundPlayer
import com.flaviotps.durmabem.custom.widgets.SoundButton

class SoundGridAdapter(private val context: Context,
                       private val mediaPlayerPool: MediaPlayerPool,
                       private val soundList: MutableList<SoundModel>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return SoundButton(context).apply {

            var soundModel = getItem(position)

            mediaPlayerPool.find(soundModel.soundResource)?.let {
                soundModel = it.soundModel
                setActive(it.isPlaying())
                setSeekBarProgress(soundModel.volume)
            }

            setText(soundModel.soundName)
            setIcon(soundModel.soundIcon)
            setOnClickListener {
                val soundPlayer = SoundPlayer(soundModel,MediaPlayer.create(context,soundModel.soundResource))
                with(mediaPlayerPool){
                    if(isActive()) {
                        addMediaPlayer(soundPlayer)
                        play(soundModel)
                    }else{
                        pause(soundModel)
                    }
                }
            }

            setVolumeListener(object : SoundButton.VolumeListener {
                override fun onVolumeChanged(volume: Float) {
                    soundModel.volume = volume
                    mediaPlayerPool.volume(soundModel)
                }

            })
        }
    }

    override fun getItem(position: Int): SoundModel {
        return soundList[position]
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getCount(): Int {
       return soundList.size
    }
}