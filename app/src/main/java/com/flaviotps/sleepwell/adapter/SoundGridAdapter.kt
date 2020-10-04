package com.flaviotps.sleepwell.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.flaviotps.sleepwell.managers.MediaPlayerPoolManager
import com.flaviotps.sleepwell.model.SoundInfo
import com.flaviotps.sleepwell.model.SoundPlayer
import com.flaviotps.sleepwell.widgets.SoundButton

class SoundGridAdapter(private val context: Context,
                       private val mediaPlayerPoolManager: MediaPlayerPoolManager,
                       private val soundList: MutableList<SoundInfo>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return SoundButton(context).apply {

            var soundModel = getItem(position)

            mediaPlayerPoolManager.find(soundModel.soundResource)?.let {
                soundModel = it.soundInfo
                setActive(it.isPlaying())
                setSeekBarProgress(soundModel.volume)
            }

            setText(soundModel.soundName)
            setIcon(soundModel.soundIcon)
            setOnClickListener {
                val soundPlayer = SoundPlayer(soundModel)
                with(mediaPlayerPoolManager){
                    if(isActive()) {
                        addMediaPlayer(soundPlayer)
                        play(context,soundModel)
                    }else{
                        stop(soundModel)
                    }
                }
            }

            setVolumeListener(object : SoundButton.VolumeListener {
                override fun onVolumeChanged(volume: Float) {
                    soundModel.volume = volume
                    mediaPlayerPoolManager.volume(soundModel)
                }

            })
        }
    }

    override fun getItem(position: Int): SoundInfo {
        return soundList[position]
    }

    override fun getItemId(position: Int): Long {
       return 0
    }

    override fun getCount(): Int {
       return soundList.size
    }
}