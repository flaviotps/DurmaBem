package com.flaviotps.durmabem.custom

import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.model.SoundInfo
import com.flaviotps.durmabem.custom.model.SoundPlayer
import com.flaviotps.durmabem.custom.model.SoundPool

class PresetRepository {
    fun getPresets(): MutableList<SoundPool> {
        val list = mutableListOf<SoundPool>()

       list.add(
           SoundPool("","Rain with Thunder",R.drawable.thunder,
               SoundPlayer(
                   SoundInfo(
                   "Nature Thunder",
                   R.raw.nature_thunder,
                   R.drawable.rain
               )
           ),         SoundPlayer(
                   SoundInfo(
                       "Nature Rain",
                       R.raw.nature_heavy_rain,
                       R.drawable.rain
                   )
               )))




        return list
    }
}