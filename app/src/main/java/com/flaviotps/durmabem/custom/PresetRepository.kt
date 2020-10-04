package com.flaviotps.durmabem.custom

import com.flaviotps.durmabem.R
import com.flaviotps.durmabem.custom.model.SoundInfo
import com.flaviotps.durmabem.custom.model.SoundPlayer
import com.flaviotps.durmabem.custom.model.SoundPool

class PresetRepository {
    fun getPresets(): MutableList<SoundPool> {
        val list = mutableListOf<SoundPool>()

       list.add(
           SoundPool("","Heavy Rain",R.drawable.thunder_thumb,
               SoundPlayer(
                   SoundInfo(
                   "Heavy Rain",
                   R.raw.nature_heavy_rain,
                   R.drawable.rain,
                   loopOffset = 2000
               )
           )))

        list.add(
            SoundPool("","Ocean",R.drawable.ocean_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Ocean",
                        R.raw.nature_sea,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Wind",R.drawable.wind_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Wind",
                        R.raw.nature_wind,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Forest",R.drawable.forest_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Forest",
                        R.raw.nature_forest,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Water Flow",R.drawable.water_flow_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Water Flow",
                        R.raw.nature_water_flow,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Night",R.drawable.night_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Night",
                        R.raw.night,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Farm",R.drawable.farm_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Farm",
                        R.raw.farm,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Snow",R.drawable.snow_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Snow",
                        R.raw.snow,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))

        list.add(
            SoundPool("","Waterfall",R.drawable.waterfall_thumb,
                SoundPlayer(
                    SoundInfo(
                        "Waterfall",
                        R.raw.waterfall,
                        R.drawable.rain,
                        loopOffset = 2000
                    )
                )))


        return list
    }
}