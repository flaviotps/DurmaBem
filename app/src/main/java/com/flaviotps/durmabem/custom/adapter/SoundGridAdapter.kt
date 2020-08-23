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
            setText(getItem(position).soundName)
            setIcon(getItem(position).soundIcon)
            mediaPlayerPool.find(getItem(position).soundResource)?.isPlaying()?.let { setActive(it) }
            setOnClickListener {
                val soundPlayer = SoundPlayer(getItem(position),MediaPlayer.create(context,getItem(position).soundResource))
                with(mediaPlayerPool){
                    if(isActive()) {
                        addMediaPlayer(soundPlayer)
                        play(soundPlayer)
                    }else{
                        pause(soundPlayer)
                    }
                }
            }
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