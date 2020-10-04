package com.flaviotps.sleepwell.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.ext.setFromResource
import com.flaviotps.sleepwell.model.SoundPool
import com.flaviotps.sleepwell.services.SoundService


class PresetsGridAdapter(
    private val context: Context,
    private val binder: SoundService.SoundServiceBinder,
    private val soundPoolList: MutableList<SoundPool>
): BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_preset, parent, false)
        val thumb = view.findViewById<ImageView>(R.id.thumb)
        val title = view.findViewById<TextView>(R.id.title)

        title.text = getItem(position).title
        getItem(position).image?.let { thumb.setFromResource(context, it) }

        thumb.setOnClickListener {
            binder.getMediaPlayerPool().stopAll()
            binder.getMediaPlayerPool().setSoundPool(getItem(position))
            binder.getMediaPlayerPool().playAll(context)
            binder.getService().createNotification();
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