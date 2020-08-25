package com.flaviotps.durmabem.custom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.model.SoundInfo

class MenuAdapter(private val context:Context, private val presetList: MutableList<SoundInfo>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.preset_list_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
      return presetList.size
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuViewHolder, position: Int) {
    }
}