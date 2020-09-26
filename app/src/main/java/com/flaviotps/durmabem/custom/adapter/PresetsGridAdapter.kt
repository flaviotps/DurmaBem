package com.flaviotps.durmabem.custom.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.commons.MediaPlayerPoolManager
import com.flaviotps.durmabem.custom.model.SoundPool
import jp.wasabeef.blurry.Blurry
import java.io.IOException
import java.io.InputStream


class PresetsGridAdapter(
    private val context: Context,
    private val mediaPlayerPoolManager: MediaPlayerPoolManager,
    private val soundPoolList: MutableList<SoundPool>
): BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =  LayoutInflater.from(context).inflate(R.layout.preset_list_item, parent, false)
        val thumb = view.findViewById<ImageView>(R.id.thumb)

        thumb.setOnClickListener {
            mediaPlayerPoolManager.stopAll()
            mediaPlayerPoolManager.setSoundPool(getItem(position))
            mediaPlayerPoolManager.playAll()
        }
        return view
    }

    override fun getItem(position: Int): SoundPool {
        return soundPoolList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return soundPoolList.size
    }
}