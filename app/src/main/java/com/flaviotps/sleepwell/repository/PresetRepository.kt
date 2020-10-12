package com.flaviotps.sleepwell.repository

import android.content.Context
import com.flaviotps.sleepwell.Category
import com.flaviotps.sleepwell.R
import com.flaviotps.sleepwell.model.SoundInfo
import com.flaviotps.sleepwell.model.SoundPlayer
import com.flaviotps.sleepwell.model.SoundPool

class PresetRepository(val context: Context) {
    fun getPresets(category: Category = Category.NONE): MutableList<SoundPool> {
        val list = mutableListOf<SoundPool>()
        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_melody),R.drawable.meditation_stones,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_melody),
                        R.raw.meditation_stones,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_piano),R.drawable.meditation_piano,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_piano),
                        R.raw.meditation_piano,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_hope),R.drawable.hope,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_hope),
                        R.raw.hope_for_better,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_flute),R.drawable.meditation_flute,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_flute),
                        R.raw.meditation_flute,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_harmony),R.drawable.melody,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_harmony),
                        R.raw.harmony,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))


        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_bowl),R.drawable.meditation_bowl,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_bowl),
                        R.raw.meditation_bowl,
                        R.drawable.meditation,
                        loopOffset = 2000,
                        category = Category.MEDITATION
                    )
                )))



        list.add(
            SoundPool("",context.getString(R.string.preset_meditation_bell),R.drawable.meditation_bell,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_bell),
                        R.raw.meditation_bells,
                        R.drawable.meditation,
                        loopOffset = 1800,
                        category = Category.MEDITATION
                    )
                )))






        list.add(
            SoundPool("",context.getString(R.string.mixer_meditation_windchimes),R.drawable.meditation_wind_chimes,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_meditation_windchimes),
                        R.raw.meditation_wind_chimes,
                        R.drawable.meditation,
                        loopOffset = 2500,
                        category = Category.MEDITATION
                    )
                )))






        list.add(
           SoundPool("",   context.getString(R.string.mixer_heavy_rain),R.drawable.heavy_rain,
               SoundPlayer(
                   SoundInfo(
                       context.getString(R.string.mixer_heavy_rain),
                   R.raw.nature_heavy_rain,
                   R.drawable.rain,
                   loopOffset = 1800,
                   category = Category.RAIN
               )
           )))

        list.add(
            SoundPool("",context.getString(R.string.preset_thunder_Storm),R.drawable.thunder_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_heavy_rain),
                        R.raw.nature_heavy_rain,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.RAIN
                    )
                ),    SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_thunder),
                        R.raw.nature_thunder,
                        R.drawable.thunder,
                        loopOffset = 0,
                        category = Category.RAIN
                    )
                )))

        list.add(
            SoundPool("",    context.getString(R.string.mixer_soft_rain),R.drawable.soft_rain,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_soft_rain),
                        R.raw.nature_rain,
                        R.drawable.rain,
                        loopOffset = 2500,
                        category = Category.RAIN
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_rain_roof),R.drawable.rain_on_roof,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_rain_roof),
                        R.raw.rain_on_roof,
                        R.drawable.rain,
                        loopOffset = 2500,
                        category = Category.RAIN
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_rain_umbrella),R.drawable.rain_on_umbrella,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_rain_umbrella),
                        R.raw.rain_on_umbrella,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.RAIN
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_rain_window),R.drawable.rain_on_window,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_rain_window),
                        R.raw.rain_on_window,
                        R.drawable.rain,
                        loopOffset = 2500,
                        category = Category.RAIN
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_ocean),R.drawable.ocean_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_ocean),
                        R.raw.ocean,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_wind),R.drawable.wind_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_wind),
                        R.raw.nature_wind,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_forest),R.drawable.forest_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_forest),
                        R.raw.nature_forest,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_water_flow),R.drawable.water_flow_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_water_flow),
                        R.raw.nature_water_flow,
                        R.drawable.rain,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_night),R.drawable.night_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_night),
                        R.raw.night,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_farm),R.drawable.farm_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_farm),
                        R.raw.farm,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_snow),R.drawable.snow_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_snow),
                        R.raw.snow,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))

        list.add(
            SoundPool("",   context.getString(R.string.mixer_waterfall),R.drawable.waterfall_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_waterfall),
                        R.raw.waterfall,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.NATURE
                    )
                )))


        list.add(
            SoundPool("",   context.getString(R.string.mixer_firewood),R.drawable.firewood_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_firewood),
                        R.raw.nature_firewood,
                        R.drawable.rain,
                        loopOffset = 3000,
                        category = Category.NATURE
                    )
                )))


        list.add(
            SoundPool("",context.getString(R.string.mixer_crowded),R.drawable.crowded_thumb,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_crowded),
                        R.raw.crowd,
                        R.drawable.rain,
                        loopOffset = 1800,
                        category = Category.URBAN
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_city_rails),R.drawable.city_rails,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_city_rails),
                        R.raw.city_rails,
                        R.drawable.rails,
                        loopOffset = 1800,
                        category = Category.URBAN
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_keyboard),R.drawable.keyboard_typing,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_keyboard),
                        R.raw.city_keyboard,
                        R.drawable.keyboard,
                        loopOffset = 1800,
                        category = Category.URBAN
                    )
                )))


        list.add(
            SoundPool("", context.getString(R.string.mixer_clock),R.drawable.clock_ticking,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_clock),
                        R.raw.instrument_clock,
                        R.drawable.clock,
                        loopOffset = 0,
                        category = Category.URBAN
                    )
                )))


        list.add(
            SoundPool("",  context.getString(R.string.mixer_forest_birds),R.drawable.forest_birds,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_forest_birds),
                        R.raw.animal_bird,
                        R.drawable.bird,
                        loopOffset = 1800,
                        category = Category.ANIMAL
                    )
                )))


        list.add(
            SoundPool("",   context.getString(R.string.mixer_white_noise),R.drawable.white_noise,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_white_noise),
                        R.raw.white_noise,
                        R.drawable.noise,
                        loopOffset = 1800,
                        category = Category.NOISE
                    )
                )))

        list.add(
            SoundPool("", context.getString(R.string.mixer_brown_noise),R.drawable.meditation_brown_noise,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_brown_noise),
                        R.raw.brown_noise,
                        R.drawable.noise,
                        loopOffset = 3000,
                        category = Category.NOISE
                    )
                )))

        list.add(
            SoundPool("",context.getString(R.string.mixer_pink_noise),R.drawable.noise_pink,
                SoundPlayer(
                    SoundInfo(
                        context.getString(R.string.mixer_pink_noise),
                        R.raw.pink_noise,
                        R.drawable.noise,
                        loopOffset = 1700,
                        category = Category.NOISE
                    )
                )))


        if(category != Category.NONE) {
            val temp = mutableListOf<SoundPool>()
            list.forEach {
                var found = false
                it.sounds.forEach{sp ->
                    if(sp.soundInfo.category == category){
                            found = true
                    }
                }
                if(found){
                    temp.add(it)
                }
            }
            return temp
        }



        return list
    }
}